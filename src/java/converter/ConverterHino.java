/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entidades.Hinos;
import entidades.Setores;
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
@FacesConverter(value = "converterHino")
public class ConverterHino implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

        if (value != null && value.trim().length() > 0) {
            try {
                Hinos hinos = new Hinos();
                hinos.setNumeroHino(value);
                return hinos;
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
            return String.valueOf(((Hinos) object).getNumeroHino());
        } else {
            return null;
        }
    }

}
