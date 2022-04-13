package com.inpacktu.crud.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inpacktu.crud.data.vo.RegistroVO;
import com.inpacktu.crud.service.RegistroService;

@RestController
@RequestMapping("/registro")
public class RegistroController {

	private final RegistroService regService;
	private final PagedResourcesAssembler<RegistroVO> assembler;

	@Autowired
	public RegistroController(RegistroService regService, PagedResourcesAssembler<RegistroVO> assembler) {
		this.regService = regService;
		this.assembler = assembler;
	}

	@GetMapping(value = "/{documento}", produces = { "application/json", "application/xml" })
	public RegistroVO findById(@PathVariable("documento") String doc) {
		RegistroVO regVO = regService.findById(doc);
		regVO.add(linkTo(methodOn(RegistroController.class).findById(doc)).withSelfRel());
		return regVO;
	}

	@GetMapping(produces = { "application/json", "application/xml" })
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));

		Page<RegistroVO> registros = regService.findAll(pageable);
		registros.stream().forEach(
				r -> r.add(linkTo(methodOn(RegistroController.class).findById(r.getDocumento())).withSelfRel()));

		PagedModel<EntityModel<RegistroVO>> pageModel = assembler.toModel(registros);

		return new ResponseEntity<>(pageModel, HttpStatus.OK);
	}

	@PostMapping(produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public RegistroVO create(@RequestBody RegistroVO regVO) {
		RegistroVO regisVO = regService.create(regVO);
		regisVO.add(linkTo(methodOn(RegistroController.class).findById(regisVO.getDocumento())).withSelfRel());
		return regisVO;
	}

	@PutMapping(produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml"})
	public RegistroVO update(@RequestBody RegistroVO regVO) {
		RegistroVO regisVO = regService.create(regVO);
		regisVO.add(linkTo(methodOn(RegistroController.class).findById(regisVO.getDocumento())).withSelfRel());
		return regisVO;
	}
	
	@DeleteMapping("/{documento}")
	public ResponseEntity<?> delete(@PathVariable("documento") String doc){
		regService.delete(doc);
		return ResponseEntity.ok().build();
	}
}
