<changeSet id="1" author="Ekaterina Gorshunova">
<sql>
DROP TRIGGER IF EXISTS bookhistory_delete ON bookHistory;
CREATE TRIGGER bookhistory_delete INSTEAD OF DELETE ON bookHistory
BEGIN DELETE bookHistory
WHERE  EXTRACT(YEAR FROM start_date) < 2022;
</sql>
<rollback>
DROP TRIGGER bookhistory_delete ON bookHistory;
</rollback>
</changeSet>