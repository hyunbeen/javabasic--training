package posproject;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class TModel extends AbstractTableModel {

   Vector data = new Vector();
   String[] columnName = { "메뉴명", "가격", "수량", "삭제" }; //칼럼명 지정
   

   @Override
   public int getRowCount() {
      // TODO Auto-generated method stub
      return data.size();
   }

   @Override
   public int getColumnCount() {
      // TODO Auto-generated method stub
      return columnName.length;
   }

   @Override
   public Object getValueAt(int rowIndex, int columnIndex) {
      // TODO Auto-generated method stub
      Vector temp = (Vector) data.get(rowIndex);
      return temp.get(columnIndex);
   }

   // 컬럼명을 지정하기
   public String getColumnName(int col) {
      return columnName[col];
   }

   // 각 셀의 편집모드로 변경
   public boolean isCellEditable(int row, int col) {
    
         return false;
   }

   public void setValueAt(Object value, int row, int col) {
      Vector temp = (Vector) data.get(row);
      temp.set(col, value);
      fireTableCellUpdated(row, col);
   }

   public Class getColumnClass(int c) {
      return getValueAt(0, c).getClass();
   }

   }
