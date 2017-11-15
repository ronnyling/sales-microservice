package sales.Repositories;

import sales.Model.Mapper.SalesMapper;
import sales.Model.SalesRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Slf4j
public class SalesRecordRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SalesRecord> getSalesRecord(String sales_person) {
            final String sql ="SELECT * FROM db_example.sales_record where sales_person=?";
        try {
            List<SalesRecord> allSales=jdbcTemplate.query(
                    sql,
                    new Object[]{ sales_person },
                    new SalesMapper()
            );
            return allSales;
        } catch (Exception ex) {
            log.info("No sales record found.", ex);
            return null;
        }
    }

    public String checkExist(Integer eid) {
        final String sql ="SELECT * FROM db_example.staffb where eid=?";
        try {
            jdbcTemplate.queryForObject(sql,new SalesMapper(),eid);
            return ("Exist");
        } catch (Exception ex) {
            return null;
        }
    }
}
