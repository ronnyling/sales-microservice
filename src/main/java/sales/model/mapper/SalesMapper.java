package sales.model.mapper;

import sales.model.SalesRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesMapper implements RowMapper<SalesRecord> {

    @Override
    public SalesRecord mapRow(ResultSet rs, int i) throws SQLException {
        SalesRecord ads = new SalesRecord();
        ads.setTransactionNo(rs.getInt("transaction_no"));
        ads.setTransactionAmt(rs.getInt("transaction_amt"));
        ads.setSalesPerson(rs.getString("sales_person"));
        ads.setTransactionName(rs.getString("transaction_name"));
        return ads;
    }
}