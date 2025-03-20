ALTER TABLE rooms ADD COLUMN can_book BOOLEAN;

UPDATE rooms
SET can_book = CASE
                        WHEN permission = 'jah' THEN TRUE
                        WHEN permission = 'EI' THEN FALSE
                        WHEN permission = 'PÄRING' THEN FALSE
                        ELSE NULL
    END;

ALTER TABLE rooms DROP COLUMN permission;