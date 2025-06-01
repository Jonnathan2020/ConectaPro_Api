package com.perseus.conectapro.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perseus.conectapro.DTO.ServicoDTO;
import com.perseus.conectapro.DTO.SolicitacaoServicoDTO;
import com.perseus.conectapro.Service.PerfilEmpresaClienteService;

@RestController
@RequestMapping("/perfil/empresaCliente")
public class PerfilEmpresaController {
	
	@Autowired
	private PerfilEmpresaClienteService perfilEmpresaClienteService;
	
   @GetMapping("/{idEmpresa}/solicitacoes")
   public ResponseEntity<List<SolicitacaoServicoDTO>> getSolicitacoes(@PathVariable Integer idEmpresa) {
	   return ResponseEntity.ok(perfilEmpresaClienteService.buscarSolicitacoesDaEmpresa(idEmpresa));
   }
   
   @GetMapping("/{idEmpresa}/propostas")
   public ResponseEntity<List<ServicoDTO>> getPropostas(@PathVariable Integer idEmpresa) {
	   return ResponseEntity.ok(perfilEmpresaClienteService.buscarPropostas(idEmpresa));
   }
   
   @GetMapping("/{idEmpresa}/historico")
   public ResponseEntity<List<ServicoDTO>> getHistorico(@PathVariable Integer idEmpresa) {
	   return ResponseEntity.ok(perfilEmpresaClienteService.buscarHistorico(idEmpresa));   }
   
}
