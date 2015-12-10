package br.com.quiz.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class JSFMessageUtil {

    public void sucesso(String mensagem) {
        FacesMessage facesMessage = createMessage(
            FacesMessage.SEVERITY_INFO, mensagem, "Sucesso!");
        addMessageToJsfContext(facesMessage);
    }

    public void alerta(String mensagem) {
        FacesMessage facesMessage = createMessage(
            FacesMessage.SEVERITY_WARN, mensagem, "Aviso!");
        addMessageToJsfContext(facesMessage);
    }
    
    public void erro(String mensagem) {
        FacesMessage facesMessage = createMessage(
            FacesMessage.SEVERITY_ERROR, mensagem, "Erro!");
        addMessageToJsfContext(facesMessage);
    }

    private FacesMessage createMessage(Severity severity, String mensagem, String titulo) {
        return new FacesMessage(severity, mensagem, titulo);
    }

    private void addMessageToJsfContext(FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}