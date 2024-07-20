CREATE TABLE IF NOT EXISTS pessoa (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       nome VARCHAR(100) NOT NULL,
       cpf CHAR(11) NOT NULL,
       data_nascimento DATE NOT NULL,
       email VARCHAR(100) NOT NULL,
       UNIQUE (cpf)
) ENGINE=InnoDB;


DELIMITER //

CREATE FUNCTION validate_cpf(cpf CHAR(11))
    RETURNS BOOLEAN
    DETERMINISTIC
BEGIN
    DECLARE digit1, digit2, calc1, calc2, i INT;

    SET cpf = REPLACE(cpf, '.', '');
    SET cpf = REPLACE(cpf, '-', '');

    IF LENGTH(cpf) != 11 THEN
        RETURN FALSE;
    END IF;

    SET calc1 = 0;
    SET i = 1;
    WHILE i <= 9 DO
            SET calc1 = calc1 + CAST(SUBSTRING(cpf, i, 1) AS UNSIGNED) * (11 - i);
            SET i = i + 1;
        END WHILE;

    SET digit1 = (calc1 * 10) % 11;
    IF digit1 = 10 THEN
        SET digit1 = 0;
    END IF;

    SET calc2 = 0;
    SET i = 1;
    WHILE i <= 10 DO
            SET calc2 = calc2 + CAST(SUBSTRING(cpf, i, 1) AS UNSIGNED) * (12 - i);
            SET i = i + 1;
        END WHILE;

    SET digit2 = (calc2 * 10) % 11;
    IF digit2 = 10 THEN
        SET digit2 = 0;
    END IF;

    IF SUBSTRING(cpf, 10, 1) != digit1 OR SUBSTRING(cpf, 11, 1) != digit2 THEN
        RETURN FALSE;
    END IF;

    RETURN TRUE;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER before_insert_tb_pessoa
    BEFORE INSERT ON pessoa
    FOR EACH ROW
BEGIN
    IF NOT validate_cpf(NEW.cpf) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'CPF inválido';
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER before_update_tb_pessoa
    BEFORE UPDATE ON pessoa
    FOR EACH ROW
BEGIN
    IF NOT validate_cpf(NEW.cpf) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'CPF inválido';
    END IF;
END //

DELIMITER ;
