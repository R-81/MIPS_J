import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.table.*;
public class MyTable extends JTable{// ʵ���Լ��ı����	
	// ��дJTable��Ĺ��췽��
	public MyTable(String[][] tableValues,String[] columnNames){
		//Vector rowData, Vector columnNames		
		super(tableValues,columnNames);						
		// ���ø���Ĺ��췽��	
		paintRow(); //����ż�зֱ�����Ϊ��ͬ��ɫ
	}	
	// ��дJTable���getTableHeader()����	
	public JTableHeader getTableHeader(){					
		// ������ͷ		
		JTableHeader tableHeader = super.getTableHeader();// ��ñ��ͷ����
		tableHeader.setReorderingAllowed(false);// ���ñ���в�������
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer)
			tableHeader.getDefaultRenderer();// ��ñ��ͷ�ĵ�Ԫ�����
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);// ��������������ʾ
		return tableHeader;	
	}	
	// ��дJTable���getDefaultRenderer(Class<?> columnClass)����
	public TableCellRenderer getDefaultRenderer(Class<?> columnClass){
		// ���嵥Ԫ��
		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) 
			super.getDefaultRenderer(columnClass);// ��ñ��ĵ�Ԫ�����
		cr.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);// ���õ�Ԫ�����ݾ�����ʾ
		return cr;	
	}	
	// ��дJTable���isCellEditable(int row, int column)����	
	public boolean isCellEditable(int row, int column){
		// ��񲻿ɱ༭		
		return false;	
	}
	public void paintRow() 
    {
        TableColumnModel tcm = this.getColumnModel();
        for (int i = 0, n = tcm.getColumnCount(); i < n; i++) 
        {
            TableColumn tc = tcm.getColumn(i);
            tc.setCellRenderer(new RowRenderer());
        }
    }
	private class RowRenderer extends DefaultTableCellRenderer 
    {
        public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) 
        {
            if (row % 2 == 0)
                setBackground(Color.WHITE);
            else
                setBackground(Color.LIGHT_GRAY);
            return super.getTableCellRendererComponent(t, value, isSelected,
                    hasFocus, row, column);
        }
    }

}