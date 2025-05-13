SELECT b.author_id as AUTHOR_ID, a.AUTHOR_NAME as AUTHOR_NAME, b.category as CATEGORY, sum(b.price * s.sales) as TOTAL_SALES
from book b 
    join author a
    on b.author_id = a.author_id
    join book_sales s
    on b.book_id = s.book_id
    and s.sales_date >='2022-01-01'
    and s.sales_date <'2022-02-01'
group by 
    b.author_id, b.category
order by b.author_id, b.category desc