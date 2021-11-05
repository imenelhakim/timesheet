package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	public int ajouterEntreprise(Entreprise entreprise) {

		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {

		deptRepoistory.save(dep);
		return dep.getId();
	}

	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {

		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) {
			Entreprise entrepriseManagedEntity = value.get();

			Optional<Departement> value1 = deptRepoistory.findById(depId);
			if (value1.isPresent()) {
				Departement depManagedEntity = value1.get();

				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);

			}
		}

	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {

		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent())

		{
			Entreprise entrepriseManagedEntity = value.get();

			List<String> depNames = new ArrayList<>();

			for (Departement dep : entrepriseManagedEntity.getDepartements()) {
				depNames.add(dep.getName());
			}

			return depNames;
		}
		else {
			return new ArrayList<>();

		}
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {

		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) {
			Entreprise ent = value.get();
			entrepriseRepoistory.delete(ent);

		}
	}

	@Transactional
	public void deleteDepartementById(int depId) {

		Optional<Departement> value = deptRepoistory.findById(depId);
		if (value.isPresent()) {
			Departement dep = value.get();
			deptRepoistory.delete(dep);

		}

	}

	public Entreprise getEntrepriseById(int entrepriseId) {

		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) {
			Entreprise ent = value.get();

			return ent;
		}
		else
			return null;

	}

}
