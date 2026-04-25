-- USERS

INSERT INTO users (
    id,
    create_date,
    first_name,
    last_name,
    email_address,
    user_name,
    password,
    role,
    email_confirmed
) VALUES (
             '00000000-0000-0000-0000-000000000001',
             CURRENT_TIMESTAMP,
             'Huseyin',
             'Aydin',
             'huseyin@example.com',
             'haydin',
             '$2a$10$q.yCIdp.pD7iVp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.',
             'ADMIN',
             true
         );

INSERT INTO users (
    id,
    create_date,
    first_name,
    last_name,
    email_address,
    user_name,
    password,
    role,
    email_confirmed
) VALUES (
             '00000000-0000-0000-0000-000000000002',
             CURRENT_TIMESTAMP,
             'Test',
             'User',
             'test@example.com',
             'testuser',
             '$2a$10$q.yCIdp.pD7iVp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.Vp.',
             'USER',
             true
         );

-- ENTRIES

INSERT INTO entry (
    id,
    create_date,
    subject,
    content,
    created_by_id
) VALUES (
             '11111111-1111-1111-1111-111111111111',
             CURRENT_TIMESTAMP,
             'Spring Boot Hakkında',
             'Spring Boot harika bir framework!',
             '00000000-0000-0000-0000-000000000001'
         );

INSERT INTO entry (
    id,
    create_date,
    subject,
    content,
    created_by_id
) VALUES (
             '11111111-1111-1111-1111-111111111112',
             CURRENT_TIMESTAMP,
             'Java 17',
             'Java 17 ile gelen yenilikler çok kullanışlı.',
             '00000000-0000-0000-0000-000000000002'
         );

-- COMMENTS

INSERT INTO entry_comment (
    id,
    create_date,
    content,
    entry_id,
    created_by_id
) VALUES (
             '22222222-2222-2222-2222-222222222221',
             CURRENT_TIMESTAMP,
             'Kesinlikle katılıyorum.',
             '11111111-1111-1111-1111-111111111111',
             '00000000-0000-0000-0000-000000000002'
         );