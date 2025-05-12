SELECT
  b.author_id,
  a.author_name,
  b.category,
  SUM(bs.sales * b.price) AS total_sales
FROM BOOK       b
JOIN AUTHOR     a ON b.author_id = a.author_id
JOIN BOOK_SALES bs ON b.book_id   = bs.book_id
WHERE bs.sales_date BETWEEN '2022-01-01' AND '2022-01-31'
GROUP BY
  b.author_id,
  a.author_name,
  b.category
ORDER BY
  b.author_id   ASC,
  b.category    DESC;
