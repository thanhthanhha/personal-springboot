-- Schema.sql for RealChat application
-- HSQL Database Schema migration from DynamoDB

-- Drop tables if they exist to ensure clean creation
DROP TABLE friend_request;
DROP TABLE friend;
DROP TABLE chat_participant;
DROP TABLE message;
DROP TABLE chat;
DROP TABLE app_user;


-- Create User table 
CREATE TABLE app_user (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    image VARCHAR(255),
    password VARCHAR(100),
    password_salt VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_user_id UNIQUE (user_id),
    CONSTRAINT unique_email UNIQUE (email)
);

-- Create indexes for User table
CREATE UNIQUE INDEX idx_user_email ON app_user(email);
CREATE UNIQUE INDEX idx_user_user_id ON app_user(user_id);
CREATE INDEX idx_user_name ON app_user(name);

-- Create Friend relationship table (replacing friendlist_id array)
CREATE TABLE friend (
    id UUID PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    friend_id VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES app_user(id),
    FOREIGN KEY (friend_id) REFERENCES app_user(id),
    CONSTRAINT unique_friendship UNIQUE (user_id, friend_id)
);

-- Create index for Friend table
CREATE INDEX idx_friend_user_id ON friend(user_id);

-- Create Chat table
CREATE TABLE chat (
    id UUID PRIMARY KEY,
    chat_owner VARCHAR(50) NOT NULL,
    chatroom_name VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chat_owner) REFERENCES app_user(id)
);

-- Create index for Chat table
CREATE INDEX idx_chat_owner ON chat(chat_owner);

-- Create ChatParticipant relationship table (replacing participants array)
CREATE TABLE chat_participant (
    id UUID PRIMARY KEY,
    chat_id VARCHAR(50) NOT NULL,
    user_id VARCHAR(50) NOT NULL, 
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chat_id) REFERENCES chat(id),
    FOREIGN KEY (user_id) REFERENCES app_user(id),
    CONSTRAINT unique_chat_participant UNIQUE (chat_id, user_id)
);

---- Create index for ChatParticipant table
CREATE INDEX idx_chat_participant_user_id ON chat_participant(user_id);
CREATE INDEX idx_chat_participant_chat_id ON chat_participant(chat_id);

-- Create Message table
CREATE TABLE message (
    id UUID PRIMARY KEY,
    chatroom_id VARCHAR(50) NOT NULL,
    sender_id VARCHAR(50) NOT NULL,
    receiver_id VARCHAR(50) NOT NULL,
    text LONGVARCHAR,
    timestamp TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chatroom_id) REFERENCES chat(id),
    FOREIGN KEY (sender_id) REFERENCES app_user(id),
    FOREIGN KEY (receiver_id) REFERENCES app_user(id)
);

-- Create indexes for Message table
CREATE INDEX idx_message_chatroom ON message(chatroom_id);
CREATE INDEX idx_message_sender ON message(sender_id);
CREATE INDEX idx_message_receiver ON message(receiver_id);
CREATE INDEX idx_message_timestamp ON message(timestamp);

-- Create FriendRequest table
CREATE TABLE friend_request (
    id UUID PRIMARY KEY,
    sender_id VARCHAR(50) NOT NULL,
    receiver_id VARCHAR(50) NOT NULL,
    approved BOOLEAN DEFAULT FALSE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES app_user(id),
    FOREIGN KEY (receiver_id) REFERENCES app_user(id),
    CONSTRAINT unique_friend_request UNIQUE (sender_id, receiver_id)
);

-- Create indexes for FriendRequest table
CREATE INDEX idx_friend_request_sender ON friend_request(sender_id);
CREATE INDEX idx_friend_request_receiver ON friend_request(receiver_id);