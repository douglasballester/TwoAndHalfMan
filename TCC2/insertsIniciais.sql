INSERT INTO CLIENT(ID_CLIENT, DS_EMAIL,DS_PASSWORD,DS_STATE,DS_USER_NAME, NM_CLIENT, TP_PERMISSION, DS_PREFERRED_COIN) 
VALUES (SEQ_CLIENT.nextVal ,'gerente@gerente.com', '$2a$10$YhV58YeUEqMSGERPf8o1gOi6I8RXQGmtxQcglKrukn8GtLwx6USam', 'ACTIVE', 'GERENTE','Gerente gerente','MANAGER', 'BRL');
INSERT INTO CLIENT(ID_CLIENT, DS_EMAIL,DS_PASSWORD,DS_STATE,DS_USER_NAME, NM_CLIENT, TP_PERMISSION, DS_PREFERRED_COIN) 
VALUES (SEQ_CLIENT.nextVal ,'admin@admin.com', '$2a$10$YhV58YeUEqMSGERPf8o1gOi6I8RXQGmtxQcglKrukn8GtLwx6USam', 'ACTIVE', 'ADMIN','Admin admin','ADMINISTRATOR', 'EUR');
INSERT INTO CLIENT(ID_CLIENT, DS_EMAIL,DS_PASSWORD,DS_STATE,DS_USER_NAME, NM_CLIENT, TP_PERMISSION, DS_PREFERRED_COIN) 
VALUES (SEQ_CLIENT.nextVal ,'gerente1@gerente.com', '$2a$10$YhV58YeUEqMSGERPf8o1gOi6I8RXQGmtxQcglKrukn8GtLwx6USam', 'ACTIVE', 'GERENTE1','Gerente1 gerente1','MANAGER', 'GBP');
 

INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'ACTIVE','http://teste.com.br', 'Github', 3);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'MONTHLY', 'CNY', 265.35, 40, 1);
 
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'ACTIVE','http://teste.com.br', 'Office', 1);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'MONTHLY', 'JPY', 1030.50, 10, 2);
  
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'CANCELED','http://teste.com.br', 'Github', 1);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'MONTHLY', 'CNY', 265.35, 40, 3);
  
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'INACTIVE','http://teste.com.br', 'DellSuporte', 3);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'MONTHLY', 'BRL', 64.74, 20, 4);
  
   
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'ACTIVE','http://teste.com.br', 'Slack', 3);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'YEARLY', 'JPY', 3087.52, 2.5, 5);
  
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'ACTIVE','http://teste.com.br', 'TypeForm', 1);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'YEARLY', 'CHF', 48.98, 4.17, 6);
  
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'CANCELED','http://teste.com.br', 'TypeForm', 3);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'YEARLY', 'CHF', 48.98, 4.17, 7);
 
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'INACTIVE','http://teste.com.br', 'SQLServer', 1);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'YEARLY', 'GBP', 66.90, 7.5, 8);
  
   
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'ACTIVE','http://teste.com.br', 'Kanban', 3);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'BIANNUAL', 'EUR', 89.90, 16.7, 9);
  
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'ACTIVE','http://teste.com.br', 'BitBucket', 1);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'BIANNUAL', 'AUD', 107.50, 13.4, 10);
  
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'CANCELED','http://teste.com.br', 'Winrar', 1);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'BIANNUAL', 'CAD', 90.62, 11.6, 11);
  
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'INACTIVE','http://teste.com.br', 'AVG', 1);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'BIANNUAL', 'BRL', 194.80, 10, 12);
   
  
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'ACTIVE','http://teste.com.br', 'Avast', 3);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'QUARTERLY', 'CNY', 298.63, 15, 13);
 
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'ACTIVE','http://teste.com.br', 'SublimeText', 1);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'QUARTERLY', 'JPY', 3601.51, 11.7, 14);
  
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'CANCELED','http://teste.com.br', 'Komodo', 1);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'QUARTERLY', 'BRL', 80.92, 8.4, 15);
  
 INSERT INTO CONTRACT(ID_CONTRACT, DS_DESCRIPTION, DS_STATE, DS_WEBSITE, NM_CONTRACT, CLIENT_ID_CLIENT)
 VALUES(SEQ_CONTRACT.nextVal, 'Testando banco de dados',  'INACTIVE','http://teste.com.br', 'VisualStudio', 1);
INSERT INTO CONTRACT_VALUE(ID_CONTRACT_VALUE, DS_PERIODICITY, DS_COIN,  VL_AMOUNT_CONTRACT_VALUE, VL_MONTHLY_USD, CONTRACT_ID_CONTRACT)
 VALUES(SEQ_CONTRACT_VALUE.nextVal, 'QUARTERLY', 'JPY', 1543.55, 5, 16);
 

COMMIT;
