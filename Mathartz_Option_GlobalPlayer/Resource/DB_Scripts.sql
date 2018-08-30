'Contract Bank here we are saving all head and player deals with'

Create table TBL_Master_Contracts  (id bigint auto_increment, SECID varchar(50) not null, SYMBOL varchar(50) not null, 
EXCHANGE varchar(50) not null, INSTRUMENT varchar(50) not null, LOTSIZE varchar(50) not null, TICKSIZE varchar(50) not null, 
EXPDD varchar(50) not null,EXPMONTHYEAR varchar(50) not null, OPTTYPE varchar(50) not null, STRIKE varchar(50) not null);

'CENTRAL DATE TABLE'

CREATE TABLE TBL_CENTRAL_DATE (id bigint auto_increment, DATE varchar(50) not null);

'Trade Line Table'

Create table TBL_Trade_Line  (id bigint auto_increment, HEADID varchar(50) not null, HEADDISPLAY varchar(50) not null, HEADSYMBOL varchar(50) not null,
PLAYERDISPLAY varchar(50) , PLAYERID varchar(50), SYMBOL varchar(50) ,
EXCHANGE varchar(50), INSTRUMENT varchar(50) , LOTSIZE varchar(50), TICKSIZE varchar(50) , 
EXPDD varchar(50) ,EXPMONTHYEAR varchar(50), OPTTYPE varchar(50) , STRIKE varchar(50) );

'BEAST VIEW'
Create table TBL_BEAST_VIEW  (id bigint,  HEADDISPLAY varchar(50) not null, 
PLAYERDISPLAY varchar(50) , F1POINT varchar(50),  F2POINT varchar(50),  F3POINT varchar(50),  
F4POINT varchar(50) ,  F5POINT varchar(50) );

'FORMULA DATA'

CREATE TABLE TBL_FORMULA_DATA (ID bigint,Fname varchar(10), X double, Y double, ST varchar(25), MT varchar(25),
ET varchar(25), LCOUNT int, ROUND int, QTY int, TRADESWITCH Bool )

'TRADE INFO'

CREATE TABLE TBL_TRADE_INFO (ID BIGINT, FNAME VARCHAR(25), ORDERTYPE VARCHAR(25), WAY VARCHAR(25) , FST VARCHAR(50), ORDERID VARCHAR(50),  PRICE DOUBLE);


'ALL SELECT'

SELECT * FROM TBL_TRADE_LINE ;
SELECT * FROM TBL_FORMULA_DATA ;
SELECT * FROM TBL_TRADE_INFO;
SELECT * FROM TBL_BEAST_VIEW ;

UPDATE TBL_BEAST_VIEW set F1POINT=0,   F2POINT=0,   F3POINT=0,   F4POINT=0,   F5POINT=0 where id >0


update TBL_TRADE_INFO   set fst='Mon Jul 23 10:40:32 IST 2018' where id >0;


update TBL_FORMULA_DATA  set st='10:37', isend=false where id >0;

