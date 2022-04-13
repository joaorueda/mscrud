package com.inpacktu.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inpacktu.crud.data.vo.RegistroVO;
import com.inpacktu.crud.entity.Registro;
import com.inpacktu.crud.exception.ResourceNotFoundException;
import com.inpacktu.crud.repository.RegistroRepository;

@Service
public class RegistroService {

	private final RegistroRepository regRepository;

	@Autowired
	public RegistroService(RegistroRepository regRepository) {
		this.regRepository = regRepository;
	}
	
	public RegistroVO create(RegistroVO regVO) {
		regRepository.save(Registro.create(regVO));
		return regVO;		
	}
	
	public Page<RegistroVO> findAll(Pageable pageable){
		var page = regRepository.findAll(pageable);
		return page.map(this::convertToRegistroVO);
	}
	
	private RegistroVO convertToRegistroVO(Registro reg) {
		return RegistroVO.create(reg);
	}
	
	public RegistroVO findById(String documento) {
		var entity = regRepository.findById(documento).orElseThrow(()-> new ResourceNotFoundException("Registro não encontrado!")) ;
		return RegistroVO.create(entity);
	}
	
	public RegistroVO update(RegistroVO regVO) {
		final Optional<Registro> opReg = regRepository.findById(regVO.getDocumento());
		
		if (!opReg.isPresent()) {
			new ResourceNotFoundException("Registro não encontrado!");
		}
		
		return RegistroVO.create(regRepository.save(Registro.create(regVO)));
	}
	
	public void delete(String doc) {
		var entity = regRepository.findById(doc).orElseThrow(()-> new ResourceNotFoundException("Registro não encontrado!")) ;
		regRepository.delete((Registro) entity);
	}
}
