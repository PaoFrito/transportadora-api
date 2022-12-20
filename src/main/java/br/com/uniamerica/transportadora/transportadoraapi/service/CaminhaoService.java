package br.com.uniamerica.transportadora.transportadoraapi.service;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Caminhao;
import br.com.uniamerica.transportadora.transportadoraapi.repositoty.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class CaminhaoService {

   @Autowired
   public CaminhaoRepository caminhaoRepository;

   public Caminhao findById(Long id){
      return this.caminhaoRepository.findById(id).orElse(new Caminhao());
   }

   public List<Caminhao> listAll()
   {
      return this.caminhaoRepository.findAll();
   }

   @Transactional
   public Caminhao save(Caminhao caminhao){
      return this.caminhaoRepository.save(caminhao);
   }


   @Transactional
   public void atualizar (final Long id, final Caminhao caminhao){
      if (id.equals(caminhao.getId()) && !this.caminhaoRepository.findById(id).isEmpty()){
         this.caminhaoRepository.save(caminhao);
      }else{
         throw new RuntimeException("Id não encontrado");
      }
   }


   @Transactional
   public void excluir(final Long id, final Caminhao caminhao){
      if (id.equals(caminhao.getId()) && !this.caminhaoRepository.findById(id).isEmpty()){
         this.caminhaoRepository.delete(caminhao);
      }else{
         throw new RuntimeException("Id não encontrado");
      }
   }

}
