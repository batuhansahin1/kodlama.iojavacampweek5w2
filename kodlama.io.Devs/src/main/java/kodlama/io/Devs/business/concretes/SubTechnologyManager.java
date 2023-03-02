package kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.Devs.business.abstracts.SubTechnologyService;
import kodlama.io.Devs.business.requests.CreateSubTechnologyRequest;
import kodlama.io.Devs.business.requests.UpdateSubTechnologyRequest;
import kodlama.io.Devs.business.responses.GetAllSubTechnologyResponse;
import kodlama.io.Devs.dataAccess.abstracts.SubTechnologyRepository;
import kodlama.io.Devs.entities.concretes.SubTechnology;

@Service
public class SubTechnologyManager  implements SubTechnologyService{
private SubTechnologyRepository technologyRepository;

@Autowired
public SubTechnologyManager(SubTechnologyRepository technologyRepository) {
	
	this.technologyRepository = technologyRepository;
}

@Override
public List<GetAllSubTechnologyResponse> getAll() {
	List<SubTechnology> technologies=technologyRepository.findAll();
	List<GetAllSubTechnologyResponse> responses=new ArrayList<GetAllSubTechnologyResponse>();
	
	
	for(SubTechnology technology:technologies) {
GetAllSubTechnologyResponse response=new GetAllSubTechnologyResponse();
response.setId(technology.getId());
		response.setName(technology.getName());
		responses.add(response);
	}
	
	return responses ;
}

@Override
public void add(CreateSubTechnologyRequest createSubTechnology) {
	SubTechnology subTechnology= new SubTechnology();
	subTechnology.setName(createSubTechnology.getName());

	technologyRepository.save(subTechnology);
}

@Override
public SubTechnology getById(int id) {
	

	return technologyRepository.findById(id).get() ;
}

@Override
public void delete(int id) {
	
	
	technologyRepository.deleteById(id);	
}



@Override
public void update(UpdateSubTechnologyRequest updateRequest, int id) {
	SubTechnology subTechnology=technologyRepository.findById(id).get();
	subTechnology.setName(updateRequest.getName());
	technologyRepository.save(subTechnology);
	
}





}
