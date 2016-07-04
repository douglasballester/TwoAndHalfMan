package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.ContractDTO;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.service.ClientService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hedo
 */
@Controller
public class UsuarioController {

    @Autowired
    ClientService service;

    @ResponseBody
    @RequestMapping(value = "/buscar-gerentes-ativos", method = RequestMethod.GET)
    public List<UsuarioDTO> getGerentesAtivos() {
        return service.findAllActiveReturningDTOs();
    }
    @ResponseBody
    @RequestMapping(value = "/buscar-todos-gerentes", method = RequestMethod.GET)
    public List<UsuarioDTO> getGerentesQualquerStatus() {
        return service.findAllReturningDTOs();
    }

    @RequestMapping(value = "/gerentes", method = RequestMethod.GET)
    public String listGerentes() {
        return "gerentes";
    }

    @RequestMapping(value = "/gerente", method = RequestMethod.GET)
    public String getGerente(@RequestParam Long idGerente, Model model) {
        model.addAttribute("usuario", service.findByIdReturningDTO(idGerente));
        return "gerente";
    }

    @RequestMapping(value = "/salvar-usuario", method = RequestMethod.POST)
    public ModelAndView salvarUsuario(@ModelAttribute("usuario") @Valid UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView model = new ModelAndView();
            model.addObject("usuario", usuarioDTO);
            model.addObject("servico", new ContractDTO());
            model.addObject("guia", "usuario");
            model.setViewName("cadastro");
            return model;
        } else {
            UsuarioDTO retornado = service.salvarUsuario(usuarioDTO);
            ModelAndView model = new ModelAndView();
            model.setViewName("gerentes");
            model.addObject("sucesso",
                    retornado != null
                            ? "Usuário " + usuarioDTO.getNome() + " cadastrado com sucesso!"
                            : "Desculpe-nos, aconteceu algum erro e o usuário não pôde ser cadastrado.");
            return model;
        }
    }
    
    @RequestMapping(value = "/check-username", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkUsername(@RequestParam String username){
        return service.checkUsernameAvailability(username);
    }
}