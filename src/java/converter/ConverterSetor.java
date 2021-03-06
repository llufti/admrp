/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entidades.Setores;
import entidades.Usuarios;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Luciano
 */
@Named
@FacesConverter(value = "converterSetor")
public class ConverterSetor implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

        if (value != null && value.trim().length() > 0) {
            try {
                Usuarios usuarios = new Usuarios();
                usuarios.setIdSetor(Integer.parseInt(value));
                return usuarios;
            } catch (NumberFormatException e) {
               
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Usuarios) object).getIdSetor());
        } else {
            return null;
        }
    }

}
