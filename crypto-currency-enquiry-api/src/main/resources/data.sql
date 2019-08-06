DROP TABLE IF EXISTS currency_price;

CREATE TABLE currency_price (
  currency_name VARCHAR(10) NOT NULL,
  currency_date DATE NOT NULL,
  currency_time VARCHAR(5) NOT NULL,
  price DECIMAL(10,3) NOT NULL,
  PRIMARY KEY(currency_name, currency_date, currency_time)
);

INSERT INTO currency_price (currency_name, currency_date, currency_time, price ) VALUES
  ('BTC',	sysdate-1,	'09:15',	34.98),
  ('BTC',	sysdate-1,	'10:45',	36.13),
  ('BTC',	sysdate-1,	'12:30',	37.01),
  ('BTC',	sysdate-1,	'14:00',	35.98),
  ('BTC',	sysdate-1,	'15:30',	33.56),
  ('BTC',	sysdate-1,	'16:10',	40.00),
  ('ETH',	sysdate-1,	'09:00',	1.45 ),
  ('ETH',	sysdate-1,	'10:30',	1.87 ),
  ('ETH',	sysdate-1,	'12:45',	1.55 ),
  ('ETH',	sysdate-1,	'15:15',	2.01 ),
  ('ETH',	sysdate-1,	'17:00',	2.15 ),
  ('LTC',	sysdate-1,	'09:30',	14.32),
  ('LTC',	sysdate-1,	'11:15',	14.87),
  ('LTC',	sysdate-1,	'12:45',	15.03),
  ('LTC',	sysdate-1,	'14:00',	14.76),
  ('LTC',	sysdate-1,	'17:00',	14.15),

  ('BTC',	TO_DATE('20180507', 'yyyymmdd'),	'02:15',	25.98),
  ('BTC',	TO_DATE('20180507', 'yyyymmdd'),	'03:45',	30.13);
