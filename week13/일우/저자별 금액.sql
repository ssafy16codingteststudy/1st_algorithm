SELECT A.AUTHOR_ID, A.AUTHOR_NAME, B.CATEGORY, SUM(B.PRICE * BS.SALES) AS TOTAL_SALES FROM AUTHOR A
                                                                                               JOIN BOOK B ON A.AUTHOR_ID = B.AUTHOR_ID
                                                                                               JOIN (SELECT * FROM BOOK_SALES WHERE SALES_DATE >= '2022-01-01'
                                                                                                                                AND SALES_DATE <= '2022-01-31') BS ON B.BOOK_ID = BS.BOOK_ID
GROUP BY A.AUTHOR_ID, A.AUTHOR_NAME, B.CATEGORY
ORDER BY A.AUTHOR_ID, B.CATEGORY DESC