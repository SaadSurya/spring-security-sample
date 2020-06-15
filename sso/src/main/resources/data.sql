INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
('client-one', 'sso-server-resources,client-one-resources,client-two-resources', '$2a$10$Q0TZgM2q.j7rb2JEgA2Ya.4BsrnPwDjlNsJNWip3Zhsf9BdE7zQYG', 'full_access', 'password,authorization_code,refresh_token', 'http://localhost:8082/clientone/login/oauth2/code/client-one', 'full_access', 86400, 2592000, null, 'true'),
('client-two', 'sso-server-resources,client-one-resources,client-two-resources', '$2a$10$Q0TZgM2q.j7rb2JEgA2Ya.4BsrnPwDjlNsJNWip3Zhsf9BdE7zQYG', 'full_access', 'password,authorization_code,refresh_token', 'http://localhost:8083/clienttwo/login/oauth2/code/client-two', 'full_access', 86400, 2592000, null, 'true'),
('client-android', 'sso-server-resources,client-one-resources,client-two-resources', '$2a$10$Q0TZgM2q.j7rb2JEgA2Ya.4BsrnPwDjlNsJNWip3Zhsf9BdE7zQYG', 'full_access', 'password,refresh_token', '', 'full_access', 86400, 2592000, null, 'true'),
('client-ios', 'sso-server-resources,client-one-resources,client-two-resources', '$2a$10$Q0TZgM2q.j7rb2JEgA2Ya.4BsrnPwDjlNsJNWip3Zhsf9BdE7zQYG', 'full_access', 'password,refresh_token', '', 'full_access', 86400, 2592000, null, 'true');

INSERT INTO users(username, password, enabled)
VALUES('saad', '$2a$10$4YTRZK4YvNru24BqMBALxupdI8TX/dbyPPOzmdFhUH0YYxIpg0Pve', 1);