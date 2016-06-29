/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.web.utils.LoggedInUserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author victor.ribeiro
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toIndex(Model model) {        
        model.addAttribute("user",LoggedInUserUtils.getLoggedInUser());    
        model.addAttribute("idMesAtual", "container-mes-atual");
        model.addAttribute("idProximoMes", "container-proximo-mes");
        return "dashboard";
    }
}
