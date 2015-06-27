-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.24-log - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных internet_shop
CREATE DATABASE IF NOT EXISTS `internet_shop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `internet_shop`;


-- Дамп структуры для таблица internet_shop.access_level
CREATE TABLE IF NOT EXISTS `access_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_shop.access_level: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `access_level` DISABLE KEYS */;
INSERT INTO `access_level` (`id`, `description`) VALUES
	(1, 'Пользователь'),
	(2, 'Администратор');
/*!40000 ALTER TABLE `access_level` ENABLE KEYS */;


-- Дамп структуры для таблица internet_shop.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_orders_users_idx` (`users_id`),
  KEY `fk_orders_status1_idx` (`status_id`),
  CONSTRAINT `fk_orders_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_shop.orders: ~7 rows (приблизительно)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `users_id`, `status_id`, `date`) VALUES
	(52, 1, 2, '2015-06-24 18:13:58'),
	(57, 3, 2, '2015-06-24 21:45:52'),
	(58, 1, 2, '2015-06-27 09:11:47'),
	(59, 1, 3, '2015-06-27 09:06:04'),
	(61, 1, 2, '2015-06-27 09:11:52'),
	(62, 1, 1, '2015-06-26 14:35:39'),
	(63, 1, 1, '2015-06-27 09:21:29'),
	(64, 1, 1, '2015-06-27 09:28:22'),
	(65, 1, 1, '2015-06-27 09:28:47');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- Дамп структуры для таблица internet_shop.orders_products
CREATE TABLE IF NOT EXISTS `orders_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_id` int(11) NOT NULL,
  `products_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_has_products_products1_idx` (`products_id`),
  KEY `fk_orders_has_products_orders1_idx` (`orders_id`),
  CONSTRAINT `fk_orders_has_products_orders1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_has_products_products1` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_shop.orders_products: ~13 rows (приблизительно)
/*!40000 ALTER TABLE `orders_products` DISABLE KEYS */;
INSERT INTO `orders_products` (`id`, `orders_id`, `products_id`) VALUES
	(57, 52, 1),
	(58, 52, 17),
	(65, 57, 23),
	(66, 57, 3),
	(67, 57, 13),
	(68, 58, 13),
	(73, 62, 23),
	(74, 62, 4),
	(75, 62, 10),
	(76, 63, 2),
	(77, 63, 12),
	(78, 63, 25),
	(79, 63, 2);
/*!40000 ALTER TABLE `orders_products` ENABLE KEYS */;


-- Дамп структуры для таблица internet_shop.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_types_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `description` text NOT NULL,
  `product_pictures_id` int(11) DEFAULT NULL,
  `publisher` varchar(50) NOT NULL,
  `developer` varchar(50) NOT NULL,
  `imprint_year` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_products_product_pictures1_idx` (`product_pictures_id`),
  KEY `fk_products_product_types1_idx` (`product_types_id`),
  CONSTRAINT `fk_products_product_pictures1` FOREIGN KEY (`product_pictures_id`) REFERENCES `product_pictures` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_product_types1` FOREIGN KEY (`product_types_id`) REFERENCES `product_types` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_shop.products: ~26 rows (приблизительно)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `product_types_id`, `name`, `price`, `description`, `product_pictures_id`, `publisher`, `developer`, `imprint_year`) VALUES
	(1, 2, 'Ведьмак 3: Дикая охота', 430000, '«Ведьмак 3: Дикая Охота» – ролевая игра нового поколения, действие которой разворачивается в удивительном фэнтезийном мире, где необходимо принимать сложные решения и отвечать за их последствия.', 1, '1С-СофтКлаб', 'CD Projekt Red', 2015),
	(2, 2, 'Diablo 3', 450000, 'С момента выхода оригинальной Diablo прошло 15 лет, но игру, заложившую основы жанра ролевых экшенов, миллионы фанатов любят и помнят до сих пор. Спустя годы история противостояния героев и демонов возвращается в новом облике, сохранив при этом все свое очарование и фирменный стиль.', 2, '1С-СофтКлаб', 'Blizzard Entertainment', 2012),
	(3, 2, 'Dark Souls 2. Scholar of the First Sin', 380000, 'Продолжение знаменитого ролевого экшена вновь заставит игроков пройти через сложнейшие испытания. Dark Souls II предложит нового героя, новую историю и новый мир. Лишь одно неизменно - выжить в мрачной вселенной Dark Souls очень непросто.', 3, 'Новый Диск', 'From Software', 2015),
	(4, 2, 'Wasteland 2', 230000, 'Wasteland 2 является прямым продолжением игры 1988-го года Wasteland - первой постапокалиптичейской CRPG и прародителя небезызвестной Fallout. Игра собрала запрошенную на Kickstarter сумму всего за 48 часов, а за все время кампании утроила ее и достигла отметки почти в три миллиона долларов.', 4, 'Бука', 'InXile Entertainment', 2014),
	(5, 2, 'The Elder Scrolls V: Skyrim', 200000, 'После убийства короля Скайрима империя оказалась на грани катастрофы. Вокруг претендентов на престол сплотились новые союзы, и разгорелся конфликт. К тому же, как предсказывали древние свитки, в мир вернулись жестокие и беспощадные драконы.', 5, '1С-СофтКлаб', 'Bethesda Softworks', 2011),
	(6, 1, 'Deus Ex: Human Revolution', 220000, 'Культовая серия DEUS EX возвращается! И вновь вас ждет великолепный, продуманный мир в классическом духе киберпанка. Человечество застыло на перекрестке и не в силах сделать последний шаг на пути к вершинам развития. Генетические модификации, кибернетика и нанотехнологии подарят единственный шанс на спасение… или станут причиной всеобщей гибели. В роли Адама Дженсена, элитного сотрудника службы безопасности, вы сможете раскрыть самые мрачные тайны этой потрясающей вселенной.', 6, 'Новый Диск', 'Eidos, Nixxes Software, Square Enix', 2011),
	(7, 1, 'S.T.A.L.K.E.R.: Тень Чернобыля', 190000, 'S.T.A.L.K.E.R. - Игра в жанре survival FPS, действие которой разворачивается в недалеком будущем в Чернобыльской зоне отчуждения.', 7, '1С-СофтКлаб', 'GSC Game World', 2007),
	(8, 1, 'Batman: Аркхем Сити', 250000, 'Студия Rocksteady — создатели проекта «Batman: Аркхем Сити» — приглашают игроков вновь окунуться в мрачную и таинственную атмосферу Готэма в игре «Batman: Аркхем Сити».', 8, '1С-СофтКлаб', 'Rocksteady Studios, Warner', 2011),
	(9, 1, 'The Evil Within', 280000, 'The Evil Within - стопроцентный триллер. Неудивительно, ведь разработкой занимается первопроходец жанра Синдзи Миками, создатель всемирно известной серии Resident Evil. Пытаясь вырваться из паутины ужаса, вы столкнетесь с неведомым и узнаете, что такое настоящий страх.', 9, '1С-СофтКлаб', 'Bethesda Softworks, Tango Gameworks', 2014),
	(10, 1, 'DmC Devil May Cry', 160000, 'А вы знали, что заносчивый полудемон Данте — гроза демонов всех мастей — не всегда был белокурым красавцем в алом плаще? В DmC Devil May Cry — пятой главе прославленной экшен-серии — вы сможете увидеть, с чего начался путь героя. И пускай пока Данте лишь ершистый подросток, он уже очень лихо орудует легендарным мечом Rebellion и парой пистолетов. А значит, вас ждут стильные битвы, полчища противников и харизматичный, неповторимо самоуверенный герой.', 10, '1С-СофтКлаб', 'Capcom, Ninja Theory', 2013),
	(11, 3, 'StarCraft II: Wings of Liberty', 300000, 'StarCraft 2 - продолжение эпической саги о трех могущественных расах: протоссах, терранах и зергах. Им предстоит снова сойтись в бою в новой стратегии в реальном времени, продолжении легендарной игры StarCraft. В этой жестокой борьбе за выживание в космосе в вашем распоряжении будут как новые боевые единицы, так и прежние, с расширенными возможностями.', 11, '1С-СофтКлаб', 'Blizzard Entertainment', 2010),
	(12, 3, 'Sid Meier`s Civilization 5. Золотое издание', 90000, 'Пятая часть позволит всем желающим насладиться великолепной графикой, переработанной боевой системой, открывающей перед игроками новые тактические решения и улучшенной дипломатией.', 12, '1С-СофтКлаб', '2K Games', 2012),
	(13, 3, 'Герои Меча и Магии 5: Золотое издание', 100000, 'Погрузитесь в захватывающий мир с неподражаемыми спецэффектами нового поколения, потрясающими эпическими сражениями, разнообразными вариантами многопользовательской игры и RPG элементами.', 13, 'Бука', 'Nival Interactive', 2007),
	(14, 3, 'Total War: Rome 2', 210000, 'Возьмите под свое командование самую сильную армию времен Древнего мира, превратите свою страну в великую империю и заставьте врагов склониться перед вашей военной, экономической и политической мощью. Разумеется, одни будут восхищаться вашими успехами, а другие станут завидовать, не исключено, что в числе последних окажутся и ваши ближайшие соратники.', 14, '1С-СофтКлаб', 'Creative Assembly', 2014),
	(15, 3, 'Виктория 2', 30000, 'Глобальная историческая стратегия, предлагающая игрокам взять в свои руки бразды правления одной из стран мира на столетний период - с 1836 по 1936 год - и, проявив талант полководца, дипломата или экономиста, сделать свое государство самой могущественной державой на планете.', 15, '1С-СофтКлаб', 'Paradox Interactive', 2010),
	(16, 4, 'Football Manager 2015', 290000, 'Игра Football Manager, прославившаяся непревзойденной реалистичностью, позволяет каждому взять в свои руки управление одним из футбольных клубов более чем 50 стран, включая все крупнейшие европейские лиги.', 16, '1С-СофтКлаб', 'SEGA, Sports Interactive', 2014),
	(17, 4, 'FIFA 15', 600000, 'FIFA 15 поднимает реалистичность виртуального футбола на новую высоту! Неистовый рев трибун, живое описание происходящего на поле от лучших спортивных комментаторов, невероятно реалистичное поведение спортсменов, подробная детализация окружающей обстановки – в новом сезоне FIFA продумано и проработано все до мелочей. Футбол – игра чрезвычайно азартная, зрелищная и исполненная неподдельного драматизма, и FIFA 15 дает возможность всем и каждому прочувствовать это по-настоящему.', 17, '1С-СофтКлаб', 'Electronic Arts', 2014),
	(18, 4, 'Pro Evolution Soccer 2015', 380000, '«Хозяева поля» - девиз игры Pro Evolution Soccer 2015 метко отражает концепцию нового сезона знаменитой серии футбольных симуляторов. Благодаря целому комплексу нововведений и усовершенствований, коснувшихся системы управления, искусственного интеллекта, визуальной составляющей и прочих аспектов, PES 2015 позволяет с головой окунуться в волнительную атмосферу настоящего футбольного матча и ощутить нешуточный накал страстей, царящий на стадионе.', 18, '1С-СофтКлаб, Konami Digital Entertainment', 'PES Productions, KONAMI Digital Entertainment', 2014),
	(19, 4, 'Formula 1 2014', 280000, 'На трассах FORMULA ONE под пронзительный рев моторов рождаются настоящие легенды мира автогонок. Каждый новый заезд - непредсказуемый, волнующий праздник для миллионов поклонников этого великолепного и динамичного спорта. Настало время перенестись на самые престижные треки планеты с новой игрой невероятно популярной серии гоночных симуляторов F1!', 19, 'Новый Диск', ' 	Codemasters Studios', 2014),
	(20, 4, 'Project CARS', 410000, 'Встречайте самый аутентичный, реалистичный, драйвовый и технически продвинутый гоночный симулятор в истории жанра! Эта игра прошла двойную проверку - свое полное одобрение высказали как многочисленные поклонники проекта в интернет-сообществах, так и профессиональные пилоты гоночных болидов, а таланты разработчиков из Slightly Mad Studios позволили блестящим образом воплотить в жизнь самые смелые задумки.', 20, 'Новый Диск', 'Slightly Mad Studios', 2015),
	(21, 5, 'Нэнси Дрю. Усыпальница пропавшей королевы', 90000, 'Многие верят в проклятие фараонов, которое непременно настигнет тех, кто нарушит покой властителей древности. Вот и теперь странные события происходят на месте раскопок, где была обнаружена загадочная усыпальница. Что это: древнее проклятие или коварный заговор с целью сорвать экспедицию? На помощь исследователям спешит непревзойденная Нэнси Дрю!', 21, 'Новый Диск', 'Simon & Schuster', 2013),
	(22, 5, 'Obscure 2', 40000, 'Два года минуло с тех пор, как исчадия ада атаковали обыкновенную среднюю школу, явившись на зов директора-маньяка. Храбрые подростки сражались до победного конца, и многие погибли в неравной схватке. Выжившие предпочли забыть о случившемся кошмаре и поступили в университет. Но страшная месть не за горами. В результате эксперимента с загадочными черными растениями, появившимися на территории студенческого кампуса, было обнаружено вещество, вызывающее яркие видения. Прежде чем герои догадаются, чем в действительности чреваты занятия ботаникой, пышно распустятся цветы зла и созреют адские ягодки. ', 22, '1С-СофтКлаб', 'Hydravision Entertainment', 2007),
	(23, 5, 'Пенумбра. Трилогия', 70000, 'Проект "Пенумбра" – серия мрачных и мистических приключений от первого лица, завоевавшая признание среди поклонников жанра ужасов и получившая хорошие оценки в международной прессе.', 23, '1С-СофтКлаб', 'Frictional Games', 2010),
	(24, 5, 'Dreamfall: The Longest Journey', 100000, 'Продолжение одной из лучших адвенчур всех времен и народов The Longest Journey. Кудесники из Funcom, известные также своей sci-fi MMORPG Anarchy Online, подготовили игрокам новое приключение, которое обещает превзойти предыдущий хит буквально во всем.', 24, 'Новый Диск', ' 	FunCom', 2005),
	(25, 5, 'Машинариум', 80000, 'Город роботов Машинариум, пропахший маслом и покрытый ржавчиной, живет напряженной жизнью гигантского механизма. У каждого его жителя свои заботы, и никому нет дела до маленького робота, которого хулиганы разобрали на запчасти и выбросили на городскую свалку.', 25, '1С-СофтКлаб', 'Amanita Design', 2009);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;


-- Дамп структуры для таблица internet_shop.product_pictures
CREATE TABLE IF NOT EXISTS `product_pictures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `path_UNIQUE` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_shop.product_pictures: ~25 rows (приблизительно)
/*!40000 ALTER TABLE `product_pictures` DISABLE KEYS */;
INSERT INTO `product_pictures` (`id`, `path`) VALUES
	(8, 'images/products/Batman.jpg'),
	(12, 'images/products/Civilization_5.jpg'),
	(3, 'images/products/Dark_Souls_2.jpg'),
	(6, 'images/products/Deus_Ex.jpg'),
	(2, 'images/products/Diablo_3.jpg'),
	(10, 'images/products/DmC.jpg'),
	(24, 'images/products/Dreamfall.jpg'),
	(17, 'images/products/FIFA_15.jpg'),
	(16, 'images/products/Football_Manager_2015.jpg'),
	(19, 'images/products/Formula_1_2014.jpg'),
	(13, 'images/products/Heroes_5.jpg'),
	(25, 'images/products/Mashinarium.jpg'),
	(21, 'images/products/Nensi.jpg'),
	(22, 'images/products/Obscure_2.jpg'),
	(23, 'images/products/Penumbra.jpg'),
	(20, 'images/products/Project_CARS.jpg'),
	(18, 'images/products/Pro_Evolution_Soccer_2015.jpg'),
	(5, 'images/products/Skyrim.jpg'),
	(7, 'images/products/STALKER.jpg'),
	(11, 'images/products/Starcraft_2.jpg'),
	(9, 'images/products/The_Evil_Within.jpg'),
	(14, 'images/products/Total_War_Rome_2.jpg'),
	(1, 'images/products/Vedmak_3.jpg'),
	(15, 'images/products/Viktoriya_2.jpg'),
	(4, 'images/products/Wasteland_2.jpg');
/*!40000 ALTER TABLE `product_pictures` ENABLE KEYS */;


-- Дамп структуры для таблица internet_shop.product_types
CREATE TABLE IF NOT EXISTS `product_types` (
  `id` int(11) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_shop.product_types: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `product_types` DISABLE KEYS */;
INSERT INTO `product_types` (`id`, `description`) VALUES
	(1, 'Экшен'),
	(2, 'РПГ'),
	(3, 'Стратегия'),
	(4, 'Симулятор'),
	(5, 'Квест');
/*!40000 ALTER TABLE `product_types` ENABLE KEYS */;


-- Дамп структуры для таблица internet_shop.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_shop.status: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` (`id`, `description`) VALUES
	(1, 'Активный'),
	(2, 'Доставлен'),
	(3, 'Отменен');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;


-- Дамп структуры для таблица internet_shop.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `black_list_flag` int(11) NOT NULL,
  `access_level_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_users_access_level1_idx` (`access_level_id`),
  CONSTRAINT `fk_users_access_level1` FOREIGN KEY (`access_level_id`) REFERENCES `access_level` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы internet_shop.users: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `login`, `password`, `email`, `black_list_flag`, `access_level_id`) VALUES
	(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@gmail.com', 0, 2),
	(2, 'eugen', 'd0dcefc460177a9bf0cbb1b8209c1d70', 'eugen@mail.ru', 1, 1),
	(3, 'Senya', 'a328a40a88607769d530dbe914b880d7', 'senya@mail.ru', 0, 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
