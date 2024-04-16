SELECT DISTINCT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPERS D
JOIN SKILLCODES S
ON S.CODE & D.SKILL_CODE
WHERE S.CATEGORY LIKE 'Front%'
ORDER BY ID