/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FormatacaoCampos;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author MASSINGUE
 */
public class PermitirInteiros extends PlainDocument{
    @Override
     public void insertString(int offset,String str,javax.swing.text.AttributeSet attr) throws BadLocationException{
      super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
    }
     public void replace(int offset,String str,javax.swing.text.AttributeSet attr) throws BadLocationException{
      super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
    }
    
}