delete from datetracker_test.event;
delete from datetracker_test.user;

INSERT INTO `user` VALUES (3,'danMiller'),(1,'kiaYang'),(2,'steveSokasits');
INSERT INTO `event` VALUES (1,'Steve\'s Birthday','annual','1972-09-06',2),(2,'Lea\'s Birthday','annual','2008-10-21',2),(3,'Bradley\'s Birthday','annual','2010-10-16',2),(4,'Heather\'s Birthday','annual','1977-04-22',2),(5,'Kia birthday','one-time','1999-01-01',1),(6,'birthday','one-time','1977-01-01',3),(7,'Dr. appt','one-time','2018-12-12',1),(8,'dr appt','one-time','2018-12-01',3),(9,'2nd appt','annual','2018-12-20',3),(10,'past birthday','annual','1998-01-01',3);



