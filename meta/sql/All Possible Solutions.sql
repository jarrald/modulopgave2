# SELECT HORIZONTAL & VERTICAL WORDS
SELECT
	0 AS X, 0 AS Y, 'horizontal' AS 'Direction', word_h_0_0.Value AS Value,
    0 AS X, 0 AS Y, 'vertical' AS 'Direction', word_v_0_0.Value AS Value,
    0 AS X, 1 AS Y, 'horizontal' AS 'Direction', word_h_0_1.Value AS Value,
    1 AS X, 0 AS Y, 'vertical' AS 'Direction', word_v_1_0.Value AS Value,
    0 AS X, 2 AS Y, 'horizontal' AS 'Direction', word_h_0_2.Value AS Value,
    2 AS X, 0 AS Y, 'vertical' AS 'Direction', word_v_0_2.Value AS Value

# GET LETTERS FOR HORIZONTAL WORD x: 0, y: 0
FROM	word_letter AS word_letter_h_0_0_1
JOIN	word_letter AS word_letter_h_0_0_2 ON word_letter_h_0_0_2.Word_Id = word_letter_h_0_0_1.Word_Id AND word_letter_h_0_0_2.Offset = 1
JOIN	word_letter AS word_letter_h_0_0_3 ON word_letter_h_0_0_3.Word_Id = word_letter_h_0_0_1.Word_Id AND word_letter_h_0_0_3.Offset = 2

# GET LETTERS FOR VERTICAL WORD x: 0, y: 0
JOIN 	word_letter AS word_letter_v_0_0_1 ON word_letter_v_0_0_1.Letter_Id = word_letter_h_0_0_1.Letter_Id AND word_letter_v_0_0_1.Offset = 0 AND
		word_letter_v_0_0_1.Word_Id != word_letter_h_0_0_1.Word_Id
JOIN 	word_letter AS word_letter_v_0_0_2 ON word_letter_v_0_0_2.Word_Id = word_letter_v_0_0_1.Word_Id AND word_letter_v_0_0_2.Offset = 1
JOIN 	word_letter AS word_letter_v_0_0_3 ON word_letter_v_0_0_3.Word_Id = word_letter_v_0_0_1.Word_Id AND word_letter_v_0_0_3.Offset = 2

# GET LETTERS FOR HORIZONTAL WORD x: 0, y: 1
JOIN 	word_letter AS word_letter_h_0_1_1 ON word_letter_h_0_1_1.Letter_Id = word_letter_v_0_0_2.Letter_Id AND word_letter_h_0_1_1.Offset = 0 AND
		word_letter_h_0_1_1.Word_Id != word_letter_h_0_0_1.Word_Id AND
        word_letter_h_0_1_1.Word_Id != word_letter_v_0_0_1.Word_Id
JOIN	word_letter AS word_letter_h_0_1_2 ON word_letter_h_0_1_2.Word_Id = word_letter_h_0_1_1.Word_Id AND word_letter_h_0_1_2.Offset = 1
JOIN	word_letter AS word_letter_h_0_1_3 ON word_letter_h_0_1_3.Word_Id = word_letter_h_0_1_1.Word_Id AND word_letter_h_0_1_3.Offset = 2

# GET LETTERS FOR VERTICAL WORD x: 1, y: 0
JOIN	word_letter AS word_letter_v_1_0_1 ON word_letter_v_1_0_1.Letter_Id = word_letter_h_0_0_2.Letter_Id AND word_letter_v_1_0_1.Offset = 0 AND
		word_letter_v_1_0_1.Word_Id != word_letter_h_0_0_1.Word_Id AND
        word_letter_v_1_0_1.Word_Id != word_letter_v_0_0_1.Word_Id AND
        word_letter_v_1_0_1.Word_Id != word_letter_h_0_1_1.Word_Id
JOIN 	word_letter AS word_letter_v_1_0_2 ON word_letter_v_1_0_2.Word_Id = word_letter_v_1_0_1.Word_Id AND word_letter_v_1_0_2.Offset = 1 AND
		word_letter_v_1_0_2.Letter_Id = word_letter_h_0_1_2.Letter_Id
JOIN 	word_letter AS word_letter_v_1_0_3 ON word_letter_v_1_0_3.Word_Id = word_letter_v_1_0_1.Word_Id AND word_letter_v_1_0_3.Offset = 2

# GET LETTERS FOR HORIZONTAL WORD x: 0, y: 2
JOIN	word_letter AS word_letter_h_0_2_1 ON word_letter_h_0_2_1.Letter_Id = word_letter_v_0_0_3.Letter_Id AND word_letter_h_0_2_1.Offset = 0 AND
		word_letter_h_0_2_1.Word_Id != word_letter_v_1_0_1.Word_Id AND
        word_letter_h_0_2_1.Word_Id != word_letter_h_0_0_1.Word_Id AND
        word_letter_h_0_2_1.Word_Id != word_letter_v_0_0_1.Word_Id AND
        word_letter_h_0_2_1.Word_Id != word_letter_h_0_1_1.Word_Id
JOIN 	word_letter AS word_letter_h_0_2_2 ON word_letter_h_0_2_2.Word_Id = word_letter_h_0_2_1.Word_Id AND word_letter_h_0_2_2.Offset = 1 AND
		word_letter_h_0_2_2.Letter_Id = word_letter_v_1_0_3.Letter_Id
JOIN 	word_letter AS word_letter_h_0_2_3 ON word_letter_h_0_2_3.Word_Id = word_letter_h_0_2_1.Word_Id AND word_letter_h_0_2_3.Offset = 2

# GET LETTERS FOR VERTICAL WORD x: 2, y: 0
JOIN	word_letter AS word_letter_v_2_0_1 ON word_letter_v_2_0_1.Letter_Id = word_letter_h_0_0_3.Letter_Id AND word_letter_v_2_0_1.Offset = 0 AND
		word_letter_v_2_0_1.Word_Id != word_letter_h_0_2_1.Word_Id AND
		word_letter_v_2_0_1.Word_Id != word_letter_v_1_0_1.Word_Id AND
        word_letter_v_2_0_1.Word_Id != word_letter_h_0_0_1.Word_Id AND
        word_letter_v_2_0_1.Word_Id != word_letter_v_0_0_1.Word_Id AND
        word_letter_v_2_0_1.Word_Id != word_letter_h_0_1_1.Word_Id
JOIN 	word_letter AS word_letter_v_2_0_2 ON word_letter_v_2_0_2.Word_Id = word_letter_v_2_0_1.Word_Id AND word_letter_v_2_0_2.Offset = 1 AND
		word_letter_v_2_0_2.Letter_Id = word_letter_h_0_1_3.Letter_Id
JOIN 	word_letter AS word_letter_v_2_0_3 ON word_letter_v_2_0_3.Word_Id = word_letter_v_2_0_1.Word_Id AND word_letter_v_2_0_3.Offset = 2 AND
		word_letter_v_2_0_3.Letter_Id = word_letter_h_0_2_3.Letter_Id

# FETCH WORDS
JOIN word AS word_h_0_0 ON word_h_0_0.Id = word_letter_h_0_0_1.Word_Id
JOIN word AS word_v_0_0 ON word_v_0_0.Id = word_letter_v_0_0_1.Word_Id
JOIN word AS word_h_0_1 ON word_h_0_1.Id = word_letter_h_0_1_1.Word_Id
JOIN word AS word_v_1_0 ON word_v_1_0.Id = word_letter_v_1_0_1.Word_Id
JOIN word AS word_h_0_2 ON word_h_0_2.Id = word_letter_h_0_2_1.Word_Id
JOIN word AS word_v_0_2 ON word_v_0_2.Id = word_letter_v_2_0_1.Word_Id

WHERE

	# FIRST LETTER IN HORIZONTAL WORD x: 0, y: 0
	word_letter_h_0_0_1.Offset = 0
;