USE modulopgave2;

# select words
SELECT DISTINCT
	(SELECT GROUP_CONCAT(Id) FROM word
		WHERE	word.Id = connection_1_1.Word_Id
		OR		word.Id = connection_2_1.Word_Id
        OR		word.Id = connection_3_1.Word_Id
        OR		word.Id = connection_4_1.Word_Id
        OR		word.Id = connection_5_1.Word_Id
        OR		word.Id = connection_6_1.Word_Id
		ORDER BY word.Id ASC) AS Ident,
        
	word_1.Value AS x0y0h,
    word_2.Value AS x0y0v,
    word_3.Value AS x0y1h,
    word_4.Value AS x1y0v,
    word_5.Value AS x0y2h,
    word_6.Value AS x2y0v


# 1.1 -> 2.1   
# 1.2 -> 4.1
# 1.3 -> 6.1 
# 2.2 -> 3.1
# 2.3 -> 5.1
# 3.2 -> 4.2
# 3.3 -> 6.2
# 4.3 -> 5.2
# 5.3 -> 6.3


FROM word AS word_1

# connection 1.1 -> 2.1
JOIN word_letter AS connection_1_1
				ON	connection_1_1.Word_Id		=	word_1.Id
                AND connection_1_1.Offset		=	0
JOIN			word_letter AS connection_2_1
				ON	connection_2_1.Letter_Id	=	connection_1_1.Letter_Id
                AND connection_2_1.Offset		=	connection_1_1.Offset
				AND connection_2_1.Word_Id		!=	connection_1_1.Word_Id

# connection 1.2 -> 4.1
JOIN word_letter AS connection_1_2
				ON	connection_1_2.Word_Id = connection_1_1.Word_Id
                AND	connection_1_2.Offset = 1
JOIN word_letter AS connection_4_1
				ON	connection_4_1.Letter_Id = connection_1_2.Letter_Id
                AND	connection_4_1.Offset = 0
                # unique word
				AND connection_4_1.Word_Id != connection_1_1.Word_Id
                AND connection_4_1.Word_Id != connection_2_1.Word_Id

# connection 1.3 -> 6.1
JOIN		word_letter AS connection_1_3
				ON	connection_1_3.Word_Id		=	connection_1_1.Word_Id
                AND connection_1_3.Offset		=	2
JOIN		word_letter AS connection_6_1
				ON	connection_6_1.Letter_Id	=	connection_1_3.Letter_Id
                AND connection_6_1.Offset		=	0
                # unique word
                AND connection_6_1.Word_Id		!=	connection_1_1.Word_Id
                AND connection_6_1.Word_Id		!=	connection_2_1.Word_Id
                AND connection_6_1.Word_Id		!=	connection_4_1.Word_Id
                
# connection 2.2 -> 3.1
JOIN		word_letter AS connection_2_2
				ON	connection_2_2.Word_Id		=	connection_2_1.Word_Id
                AND connection_2_2.Offset		=	1
JOIN		word_letter AS connection_3_1
				ON	connection_3_1.Letter_Id	=	connection_2_2.Letter_Id
                AND connection_3_1.Offset		=	0
                # unique word
                AND connection_3_1.Word_Id		!=	connection_1_1.Word_Id
                AND connection_3_1.Word_Id		!=	connection_2_1.Word_Id
                AND connection_3_1.Word_Id		!=	connection_4_1.Word_Id
                AND connection_3_1.Word_Id		!=	connection_6_1.Word_Id


# connection 2.3 -> 5.1
JOIN		word_letter AS connection_2_3
				ON	connection_2_3.Word_Id		=	connection_2_1.Word_Id
                AND connection_2_3.Offset		=	2
JOIN		word_letter AS connection_5_1
				ON	connection_5_1.Letter_Id	=	connection_2_3.Letter_Id
                AND connection_5_1.Offset		=	0
                # unique word
                AND connection_5_1.Word_Id		!=	connection_1_1.Word_Id
                AND connection_5_1.Word_Id		!=	connection_2_1.Word_Id
                AND connection_5_1.Word_Id		!=	connection_4_1.Word_Id
                AND connection_5_1.Word_Id		!=	connection_6_1.Word_Id
                AND connection_5_1.Word_Id		!=	connection_3_1.Word_Id
                
                
# connection 3.2 -> 4.2
JOIN		word_letter AS connection_3_2
				ON	connection_3_2.Word_Id		=	connection_3_1.Word_Id
                AND connection_3_2.Offset		=	1
JOIN		word_letter AS connection_4_2
				ON	connection_4_2.Word_Id		=	connection_4_1.Word_Id
				AND	connection_4_2.Letter_Id	=	connection_3_2.Letter_Id
                AND connection_4_2.Offset		=	1
                
                
# connection 3.3 -> 6.2
JOIN		word_letter AS connection_3_3
				ON	connection_3_3.Word_Id		=	connection_3_1.Word_Id
                AND connection_3_3.Offset		=	2
JOIN		word_letter AS connection_6_2
				ON	connection_6_2.Word_Id		=	connection_6_1.Word_Id
				AND	connection_6_2.Letter_Id	=	connection_3_3.Letter_Id
                AND connection_6_2.Offset		=	1
                
                
# connection 4.3 -> 5.2
JOIN		word_letter AS connection_4_3
				ON	connection_4_3.Word_Id		=	connection_4_1.Word_Id
                AND connection_4_3.Offset		=	2
JOIN		word_letter AS connection_5_2
				ON	connection_5_2.Word_Id		=	connection_5_1.Word_Id
				AND	connection_5_2.Letter_Id	=	connection_4_3.Letter_Id
                AND connection_5_2.Offset		=	1
                
                
# connection 5.3 -> 6.3
JOIN		word_letter AS connection_5_3
				ON	connection_5_3.Word_Id		=	connection_5_1.Word_Id
                AND connection_5_3.Offset		=	2
JOIN		word_letter AS connection_6_3
				ON	connection_6_3.Word_Id		=	connection_6_1.Word_Id
				AND	connection_6_3.Letter_Id	=	connection_5_3.Letter_Id
                AND connection_6_3.Offset		=	2
                

# Join words
JOIN word AS word_2 ON word_2.Id = connection_2_1.Word_Id
JOIN word AS word_3 ON word_3.Id = connection_3_1.Word_Id
JOIN word AS word_4 ON word_4.Id = connection_4_1.Word_Id
JOIN word AS word_5 ON word_5.Id = connection_5_1.Word_Id
JOIN word AS word_6 ON word_6.Id = connection_6_1.Word_Id

GROUP BY Ident
#ORDER BY word_1.Id ASC
