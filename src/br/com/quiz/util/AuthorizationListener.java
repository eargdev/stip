package br.com.quiz.util;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {
        
        FacesContext facesContext = event.getFacesContext();

        // Página Atual.
        String currentPage = facesContext.getViewRoot().getViewId();

        // Verifica se está na página de login.
        boolean isLoginPage = (currentPage.lastIndexOf("login") > -1);
        
        /*boolean principal = (currentPage.replace(".xhtml", ".faces")
            .equals("/pages/comum/selecaoSistema.faces"));*/

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        //Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcionario_logado");

        // Redireciona para a página de login quando a sessão expira.
        //if(!isLoginPage && funcionarioLogado == null) {
            try {
            	//FuncionarioSessionController.timeOut();
                if(SessionUtil.getSession() != null) {
                    SessionUtil.getSession().invalidate();

                    FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("sessao_expirada", "S");
                }
                FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("/almoxarifado/pages/comum/login.faces");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        //} else {
            // Lógica do controle de acesso as páginas.
        //}
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // Nada...
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}