import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.table.*;
public class MyTable extends JTable{// 实现自己的表格类	
	// 重写JTable类的构造方法
	public MyTable(String[][] tableValues,String[] columnNames){
		//Vector rowData, Vector columnNames		
		super(tableValues,columnNames);						
		// 调用父类的构造方法	
		paintRow(); //将奇偶行分别设置为不同颜色
	}	
	// 重写JTable类的getTableHeader()方法	
	public JTableHeader getTableHeader(){					
		// 定义表格头		
		JTableHeader tableHeader = super.getTableHeader();// 获得表格头对象
		tableHeader.setReorderingAllowed(false);// 设置表格列不可重排
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer)
			tableHeader.getDefaultRenderer();// 获得表格头的单元格对象
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);// 设置列名居中显示
		return tableHeader;	
	}	
	// 重写JTable类的getDefaultRenderer(Class<?> columnClass)方法
	public TableCellRenderer getDefaultRenderer(Class<?> columnClass){
		// 定义单元格
		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) 
			super.getDefaultRenderer(columnClass);// 获得表格的单元格对象
		cr.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);// 设置单元格内容居右显示
		return cr;	
	}	
	// 重写JTable类的isCellEditable(int row, int column)方法	
	public boolean isCellEditable(int row, int column){
		// 表格不可编辑		
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