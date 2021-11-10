pipeline {
    environment {
        registry = "najdfeki/timesheet" 
        registryCredential = 'najdfeki'
        dockerImage = '' 
    }
    agent any
    stages{
        stage('Clone and clean repo'){
            steps {
                bat "git clone https://github.com/Najd-Feki/devops-timesheet"
                bat "mvn clean install -U -f devops-timesheet "
            }
        }
        stage('Test'){
            steps{ bat "mvn test -f devops-timesheet"}
        }
        stage('Packaging'){
            steps {
                bat "mvn package -f devops-timesheet"
            }
        }
        stage('Deployment'){
            steps {
                bat "mvn deploy -f devops-timesheet"
            }
        }
        stage('Sonar'){
            steps {
                bat "mvn sonar:sonar -f devops-timesheet"
            }
        }
        //send email
        stage('Email'){
            steps{
                 emailext body: 'You just launched a job !',  to: 'fekinajd@gmail.com', subject: 'From Jenkins'
        
            }
        }
         stage('Building our image') {
            //bat "cd timesheetimen"
            steps{
                script {
                    dir("C:/Windows/System32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/devops/devops-timesheet"){
                        dockerImage = docker.build registry + ":$BUILD_NUMBER"
                    }
                }
            }
        }
        stage('Deploy docker image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Running docker compose') {
            steps {
                script {
                    try {
                        timeout(time: 5, unit: 'MINUTES') {
                            bat"docker-compose up"
                            }
                        }
                    catch (Exception e) {
                        echo "Time is up"
                        }
                       }
                    }
        }
        stage('Cleaning up') {
            steps {
                    bat 'docker-compose down'
                    bat "docker image prune --all --force"
               }
            }        
        }
        post{
            always{
            cleanWs()
        }
    }
    }