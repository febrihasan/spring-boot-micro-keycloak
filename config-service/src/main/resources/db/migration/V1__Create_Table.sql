-- Create Table Users
CREATE TABLE USERS (
    ID                  VARCHAR(50) PRIMARY KEY NOT NULL,
    USERNAME            VARCHAR(30) NOT NULL,
    CUSTOMER_NUMBER     VARCHAR(50) NOT NULL,
    PIN                 VARCHAR(100),
    IS_ACTIVE           BOOLEAN,
    CREATED_AT          TIMESTAMP WITH TIME ZONE,
    CREATED_BY          BIGINT,
    UPDATED_AT          TIMESTAMP WITH TIME ZONE,
    UPDATED_BY          BIGINT,
    DELETED_AT          TIMESTAMP WITH TIME ZONE,
    DELETED_BY          BIGINT
);

-- Create Index Users
CREATE INDEX idx_users ON USERS USING btree(ID);

-- Create Table Accounts
CREATE TABLE ACCOUNTS (
    ID                  BIGINT PRIMARY KEY NOT NULL,
    USER_ID             VARCHAR(50) NOT NULL,
    ACCOUNT_NUMBER      VARCHAR(50) NOT NULL,
    IS_ACTIVE           BOOLEAN,
    CREATED_AT          TIMESTAMP WITH TIME ZONE,
    CREATED_BY          BIGINT,
    UPDATED_AT          TIMESTAMP WITH TIME ZONE,
    UPDATED_BY          BIGINT,
    DELETED_AT          TIMESTAMP WITH TIME ZONE,
    DELETED_BY          BIGINT
);

-- Create Index Accounts
CREATE INDEX idx_accounts ON ACCOUNTS USING btree(ID);

-- Create Table Transactions
CREATE TABLE TRANSACTIONS (
    ID                      BIGINT PRIMARY KEY NOT NULL,
    USER_ID                 VARCHAR(50) NOT NULL,
    FROM_ACCOUNT_NUMBER     VARCHAR(30) NOT NULL,
    TO_ACCOUNT_NUMBER       VARCHAR(30) NOT NULL,
    TRANSACTION_AMOUNT      NUMERIC(12,2),
    DESCRIPTION             VARCHAR(100),
    TRANSACTION_STATUS      VARCHAR(10),
    REFERENCE_NUMBER        VARCHAR(20),
    RECEIPT_IDENTIFIER      VARCHAR(20),
    CREATED_AT              TIMESTAMP WITH TIME ZONE,
    CREATED_BY              BIGINT,
    UPDATED_AT              TIMESTAMP WITH TIME ZONE,
    UPDATED_BY              BIGINT,
    DELETED_AT              TIMESTAMP WITH TIME ZONE,
    DELETED_BY              BIGINT
);

-- Create Index Transactions
CREATE INDEX idx_transactions ON TRANSACTIONS USING btree(ID);