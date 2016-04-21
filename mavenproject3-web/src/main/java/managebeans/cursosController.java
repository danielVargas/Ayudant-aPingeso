package managebeans;

import entities.cursos;
import entities.profesor;
import managebeans.util.JsfUtil;
import managebeans.util.JsfUtil.PersistAction;
import sessionsbeans.cursosFacadeLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessionsbeans.profesorFacadeLocal;

@Named("cursosController")
@SessionScoped
public class cursosController implements Serializable {

    @EJB
    private cursosFacadeLocal ejbFacade;
    private List<cursos> items = null;
    private cursos selected;
    private List<String> prof = null;
    @EJB
    private profesorFacadeLocal profacade;
    public cursosController() {
    }

    public List<String> getProf() {
        return prof;
    }

    public void setProf(List<String> prof) {
        this.prof = prof;
    }

    
    public cursos getSelected() {
        return selected;
    }

    public void setSelected(cursos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private cursosFacadeLocal getFacade() {
        return ejbFacade;
    }

    public cursos prepareCreate() {
        selected = new cursos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        List <profesor> profesores = new ArrayList<>();
        for (int i = 0; i < prof.size(); i++){
            profesor proftemp =  profacade.find(Long.valueOf(prof.get(i)));
            profesores.add(proftemp);
            
        }
        selected.setProfesores(profesores);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("cursosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("cursosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("cursosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<cursos> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public cursos getcursos(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<cursos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<cursos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = cursos.class)
    public static class cursosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            cursosController controller = (cursosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cursosController");
            return controller.getcursos(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof cursos) {
                cursos o = (cursos) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), cursos.class.getName()});
                return null;
            }
        }

    }

}
