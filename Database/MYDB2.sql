alter session set current_schema=SQL_CORSO_DB

-- elencare i venditori e gli uffici in cui lavorano

SELECT name, rep_office
    FROM salesreps;

SELECT name, city
    FROM salesreps, offices
    where rep_office = office;

SELECT name, city
    FROM salesreps
    INNER JOIN offices
        ON rep_office = office;
    --where region = 'Western';

SELECT name, city
    FROM salesreps
    LEFT JOIN offices
        ON rep_office = office;
    --where region = 'Western';

SELECT name, rep_office
    FROM salesreps;

SELECT count(*)
    FROM salesreps;

SELECT count(rep_office)
    FROM salesreps;

select * from orders
    order by rep;

SELECT S.name, round(AVG(O.amount), 2)
    FROM orders O
    Join salesreps S
        on O.rep = S.empl_num
    GROUP BY S.name
    having AVG(O.amount) > 7000
    order by S.name;


SELECT O.city
    FROM offices O
    WHERE O.target > (
        xxx
    )

SELECT SUM(S.quota)
    FROM salesreps S
    WHERE S.rep_office = yyy
    
    
    
    

SELECT O.city
    FROM offices O
    WHERE O.target > (
        SELECT SUM(S.quota)
            FROM salesreps S
            WHERE S.rep_office = O.office
    )

SELECT sum(quota)
    FROM salesreps S
    WHERE S.rep_office = 13

select office, city, target from offices;

SELECT name, CAST (rep_office AS VARCHAR2(4)), CAST (hire_date AS VARCHAR2(9))
    FROM salesreps
    
    
CREATE TABLE OLDORDERS (
    ORDER_NUM INTEGER NOT NULL,
    ORDER_DATE DATE NOT NULL,
    AMOUNT NUMBER NOT NULL check (amount > 0),
    PRIMARY KEY (ORDER_NUM));    
    
select * from orders order by order_date;
select * from oldorders order by order_date;

insert into oldorders 
    select order_num, order_date, amount
        from orders
        where order_date < '01-feb-90';
    
drop table oldorders;

insert into oldorders VALUES(1, '04-mag-2023', 12999.99);
insert into oldorders VALUES(2, '04-mag-2023', 22999.99);

create domain amount_dom as INTEGER 
    constraint amount_chk check (amount > 0);

SELECT * FROM v$version;

select * from salesreps order by manager;
select * from offices;

INSERT INTO salesreps (NAME, AGE, rep_office, TITLE, HIRE_DATE, QUOTA, SALES)
    VALUES ('Tom Snyder', 41, 44, 'Sales Rep', '13-GEN-90', NULL, 75985.00);

update salesreps set rep_office=44 where empl_num=106

/* Add a customer procedure */
CREATE or REPLACE PROCEDURE add_cust (
    p_name in VARCHAR2,         /* input customer name */
    p_num in INTEGER,           /* input customer number */
    p_cred_lim in NUMBER,       /* input credit limit */
    p_tgt_sls in NUMBER,        /* input target sales */
    p_rep in INTEGER,           /* input salesrep emp # */
    p_office in VARCHAR2) AS    /* input office city */
BEGIN
    /* Insert new row of CUSTOMERS table */
    INSERT INTO customers (cust_num, company, cust_rep, credit_limit)
        VALUES (p_num, p_name, p_rep, p_cred_lim);
        
    /* Update row of SALESREPS table */
    UPDATE salesreps
        SET quota = quota + p_tgt_sls
        WHERE empl_num = p_rep;
        
    /* Update row of OFFICES table */
    UPDATE offices
        SET target = target + p_tgt_sls
        WHERE city = p_office;
    /* Commit transaction and we are done */
    COMMIT;
END;

select * from customers;
select * from salesreps where empl_num=103;
select * from offices where city='Chicago';

// 103 passa a quota=325000
// Chicago passa a target=850000
exec add_cust('XYZ Corporation', 2122, 30000, 50000, 103, 'Chicago');

    INSERT INTO customers (cust_num, company, cust_rep, credit_limit)
        VALUES (2122, 'XYZ Corporation', 103, 30000.00);

delete from customers where cust_num=2122;

/* Check order total for a customer */
CREATE or REPLACE PROCEDURE chk_tot (
    p_num INTEGER)
AS
    /* Declare two local variables */
    v_tot_ord   NUMBER(16,2);
    v_msg_text  VARCHAR(30);
BEGIN
    /* Calculate total orders for requested customer */
    SELECT SUM(amount)
        INTO v_tot_ord
        FROM orders
        WHERE cust = p_num;
        
    /* Load appropriate message, based on total */
    IF v_tot_ord > 30000.00
    THEN
        v_msg_text := 'high order total';
    ELSE
        v_msg_text := 'low order total';
    END IF;
    dbms_output.put_line(v_msg_text);
END;


/* Return total order amount for a customer */
CREATE or REPLACE FUNCTION get_tot_ords(
    p_num IN INTEGER) RETURN NUMBER
AS
    /* Declare one local variable to hold the total */
    v_tot_ord NUMBER(16,2);
BEGIN
    /* Simple single-row query to get total */
    SELECT SUM(amount)
        INTO v_tot_ord
        FROM orders
        WHERE cust = p_num;
        
    /* return the retrieved value as fcn value */
    RETURN v_tot_ord;
END;

SELECT company
    FROM customers
    WHERE get_tot_ords(cust_num) > 30000.00;

DECLARE
    v_num INTEGER := 2103;
    v_tot_amount NUMBER;
BEGIN
    v_tot_amount := get_tot_ords(v_num);
    dbms_output.put_line('TOT = ' || v_tot_amount);
END;

select * from orders where cust=2103;
select * from customers;
select * from orders order by cust;

BEGIN
    chk_tot(2103);
END;

/* Get customer name, sales rep and office */
CREATE or REPLACE PROCEDURE get_cust_info(
    p_num                INTEGER,
    p_cust_name     OUT  VARCHAR2,
    p_rep_name      OUT  VARCHAR2,
    p_office_name   OUT  VARCHAR2) AS
BEGIN
    /* Simple single-row query to get info */
    SELECT company, name, city
        INTO p_cust_name, p_rep_name, p_office_name
        FROM customers, salesreps, offices
        WHERE cust_num = p_num
            AND empl_num = cust_rep
            AND office = rep_office;
END;

DECLARE
    v_cust_num      NUMBER:=2105;
    v_cust_name     VARCHAR2(20);
    v_rep_name      VARCHAR2(15);
    v_office_name   VARCHAR2(15);
BEGIN
    get_cust_info(v_cust_num, v_cust_name, v_rep_name, v_office_name);
    
    dbms_output.put_line(v_cust_name || 
        ': venditore = ' || v_rep_name ||
        ', ufficio vendite = ' || v_office_name);
END;


/* Add a customer procedure */
CREATE or REPLACE PROCEDURE add_cust (
    p_name          VARCHAR2,       /* input customer name */
    p_num           INTEGER,        /* input customer number */
    p_cred_lim      NUMBER,         /* input credit limit */
    p_tgt_sales     NUMBER,         /* input target sales */
    p_rep_num       INTEGER)        /* input salesrep empl # */
AS
    v_rep_office INTEGER;
BEGIN
    /* Insert new row of CUSTOMERS table */

    INSERT INTO customers (cust_num, company, cust_rep, credit_limit)
        VALUES (p_num, p_name, p_rep_num, p_cred_lim);

    /* Update row of SALESREPS table */
    IF p_tgt_sales <= 20000.00
    THEN
        UPDATE salesreps
            SET quota = quota + p_tgt_sales
            WHERE empl_num = p_rep_num;
    ELSE
        UPDATE salesreps
            SET quota = quota + 20000.00
            WHERE empl_num = p_rep_num;
    END IF;
    
    SELECT rep_office
        INTO v_rep_office
        FROM salesreps
        where  empl_num=p_rep_num;
        
    /* Update row of OFFICES table */
    UPDATE offices
        SET target = target + p_tgt_sales
        WHERE office = v_rep_office;

    /* Commit transaction and we are done */
    COMMIT;
END;

begin
    add_cust ('Apple Computers', 2123, 40000, 10000, 103);
end;

select * from customers;
select name, quota
    from salesreps where empl_num=103;
select city, target, name
    from offices, salesreps
    where rep_office = office
    and empl_num = 103;


paul cruz 325000
chicago 850000

create table SMALLORDERS (
    rep_name varchar2(15),
    ord_amount  NUMBER
);

create table BIGORDERS (
    cust_name varchar2(20),
    ord_amount  NUMBER
);

alter table SMALLORDERS add cust_name varchar2(20);
alter table BIGORDERS add rep_name varchar2(15);

CREATE or REPLACE PROCEDURE sort_orders
AS
    /* Cursor for the query */
    CURSOR v_cursor IS
        SELECT amount, company, name
            FROM orders, customers, salesreps
            WHERE cust = cust_num
                AND rep = empl_num;
                
    /* Row variable to receive query results values (RECORD) */
    v_curs_row v_cursor%ROWTYPE;
BEGIN
    /* Loop through each row of query results */
    FOR v_curs_row IN v_cursor
    LOOP
        /* Check for small orders and handle */
        IF (v_curs_row.amount < 1000.00)
        THEN
            INSERT INTO smallorders
                VALUES (v_curs_row.name, v_curs_row.amount, v_curs_row.company);
        /* Check for big orders and handle */
        ELSIF (v_curs_row.amount > 10000.00)
        THEN
            INSERT INTO bigorders
                VALUES (v_curs_row.company, v_curs_row.amount, v_curs_row.name);
        END IF;
    END LOOP;
END;

begin
    sort_orders;
end;

select * from smallorders;
select * from bigorders;


/* Return total order amount for a customer */
CREATE or REPLACE FUNCTION get_tot_ords(
    p_num integer) RETURN NUMBER
AS
    /* Declare one local variable to hold the total */
    v_tot_ord NUMBER(16,2);
    v_orders_rec orders%ROWTYPE;
BEGIN
    SELECT *
        INTO v_orders_rec
        FROM orders
        WHERE cust = p_num;
        
    /* Simple single-row query to get total */
    SELECT SUM(amount)
        INTO v_tot_ord
        FROM orders
        WHERE cust = p_num;
        
    /* return the retrieved value as fcn value */
    RETURN v_tot_ord;
EXCEPTION
    /* Handle the situation where no orders found */
    WHEN NO_DATA_FOUND
    THEN
        RAISE_APPLICATION_ERROR (-20123, 'Bad cust#');
        /*dbms_output.put_line(-20123 || ' Bad cust#');
        return -1;*/
        /* Handle any other exceptions */
    WHEN OTHERS
    THEN
        RAISE_APPLICATION_ERROR (-20199,'Unknown error');
        /*dbms_output.put_line(-20199 || ' Unknown error');
        return -1;*/
END;

DECLARE
    v_cust_num INTEGER := 2123;
    v_tot_ord NUMBER(16,2);
BEGIN
    v_tot_ord := get_tot_ords(v_cust_num);
    dbms_output.put_line('TOT = ' || v_tot_ord);
END;

select C.company, sum(O.amount)
    from customers C
    join orders O
        on O.cust = C.cust_num
    group by C.company
    having sum(amount) = 0;

select C.cust_num, C.company
    from customers C
    where cust_num not in (
        select O.cust
            from customers C
            join orders O
                on O.cust = C.cust_num       
    )

select C.cust_num, C.company
    from customers C, orders O

select C.cust_num, C.company
    from customers C
    left join orders O
        on O.cust = C.cust_num       
    where O.cust = null;
    
select * from customers;
select * from customers where company = 'Three-Way Lines';
select * from orders where cust = 2103;
insert into customers (company, cust_rep, credit_limit) values ('vattelappesca ltd.', 102, 10000.00);

select * from customers;

SELECT SUM(amount)
    FROM orders
    WHERE cust = 2321;
    
    
/* Insert trigger for SALESREPS */
CREATE or REPLACE TRIGGER upd_tgt
    BEFORE INSERT ON salesreps FOR EACH ROW
    WHEN (NEW.quota IS NOT NULL and NEW.quota != 0)
BEGIN
    UPDATE offices
        SET target = target + :NEW.quota
        where office = :NEW.rep_office;
    dbms_output.put_line('TRIGGER: RIGA INSERITA'); 
END;

select * from salesreps order by empl_num;
select * from offices;

INSERT INTO salesreps (NAME, AGE, TITLE, rep_office, HIRE_DATE, QUOTA, SALES)
    VALUES ('Tom Carrozza', 21, 'Sales Rep', 14, '13-GEN-2001', 20000.00, 0.00);

delete from salesreps where empl_num = 122;