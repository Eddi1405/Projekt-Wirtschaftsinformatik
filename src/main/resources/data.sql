MERGE INTO USERS AS target
    USING (SELECT -1 AS ID, NULL AS USERNAME, NULL AS PASSWORD, NULL AS EMAIL,
                  FALSE AS DARK_MODE, FALSE AS DE_EN, FALSE AS CONTRAST,
                  0 AS FONT_SIZE, FALSE AS EYE_TRACKING,
                  0 AS COLOR_BLINDNESS, FALSE AS SIGN_LANGUAGE,
                  'ANONYMOUS' AS ROLE, NULL AS LEARNINGTYPE) AS source
    ON target.ID = source.ID
    WHEN MATCHED THEN
        UPDATE SET target.USERNAME = source.USERNAME,
            target.PASSWORD = source.PASSWORD,
            target.EMAIL = source.EMAIL,
            target.DARK_MODE = source.DARK_MODE,
            target.DE_EN = source.DE_EN,
            target.CONTRAST = source.CONTRAST,
            target.FONT_SIZE = source.FONT_SIZE,
            target.EYE_TRACKING = source.EYE_TRACKING,
            target.COLOR_BLINDNESS = source.COLOR_BLINDNESS,
            target.SIGN_LANGUAGE = source.SIGN_LANGUAGE,
            target.ROLE = source.ROLE,
            target.LEARNINGTYPE = source.LEARNINGTYPE
    WHEN NOT MATCHED THEN
        INSERT (ID, USERNAME, PASSWORD, EMAIL, DARK_MODE, DE_EN, CONTRAST,
                FONT_SIZE, EYE_TRACKING, COLOR_BLINDNESS, SIGN_LANGUAGE, ROLE, LEARNINGTYPE)
            VALUES (-1, NULL, NULL, NULL, FALSE, FALSE, FALSE, 0, FALSE, 0, FALSE, 'ANONYMOUS', NULL);