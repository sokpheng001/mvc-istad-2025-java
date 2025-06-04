package view;


import model.dto.ProductResponseDto;
import model.dto.UserResponseDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class TableUI<T> {
    private Table table;
    private String [] columnNames;
    private final CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
    public void getTableDisplay(List<T> tList){
        if(tList.getFirst() instanceof ProductResponseDto){
            table = new Table(3, BorderStyle.UNICODE_BOX_DOUBLE_BORDER,
                    ShownBorders.ALL);
            columnNames= new String[] {"UUID","Product Name","Expired Date"};
        }
        if(tList.getFirst() instanceof UserResponseDto){
            table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER,
                    ShownBorders.ALL);
            columnNames= new String[] {"UUID","User Name","Email","Created Date"};
        }
        for(String column: columnNames){
            table.addCell(column,center);
        }
        for(T t: tList){
            if(t instanceof ProductResponseDto){
                table.addCell(((ProductResponseDto) t).uuid(),center);
                table.addCell(((ProductResponseDto )t).pName(),center);
                table.addCell(((ProductResponseDto )t).expiredDate().toString(),center);
            }
            if(t instanceof UserResponseDto){
                table.addCell(((UserResponseDto)t).uuid(), center);
                table.addCell(((UserResponseDto)t).userName(), center);
                table.addCell(((UserResponseDto)t).email(), center);
                table.addCell(((UserResponseDto)t).createdDate().toString(), center);
            }
        }
        for(int i =0;i< columnNames.length;i++){
            table.setColumnWidth(i, 40,45);
        }
        System.out.println(table.render());
    }
}
