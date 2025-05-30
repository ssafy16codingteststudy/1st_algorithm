SELECT B.AUTHOR_ID, B.AUTHOR_NAME, BS.CATEGORY, SUM(BS.TOTAL_PRICE) TOTAL_SALES
FROM (SELECT BS.BOOK_ID, BS.SALES_DATE, BS.SALES * B.PRICE TOTAL_PRICE, B.CATEGORY, B.AUTHOR_ID
      FROM BOOK_SALES BS
      JOIN BOOK B ON B.BOOK_ID = BS.BOOK_ID) BS
JOIN AUTHOR B ON B.AUTHOR_ID = BS.AUTHOR_ID
WHERE YEAR(BS.SALES_DATE) = 2022 AND MONTH(BS.SALES_DATE) = 1
GROUP BY BS.CATEGORY, B.AUTHOR_ID
ORDER BY  B.AUTHOR_ID, BS.CATEGORY DESC