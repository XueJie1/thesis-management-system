-- department 表数据
INSERT INTO `department` (`name`, `code`) VALUES
                                              ('计算机学院', 'CS'),
                                              ('数学系', 'MATH'),
                                              ('物理系', 'PHY'),
                                              ('化学系', 'CHEM'),
                                              ('生物系', 'BIO'),
                                              ('经济学院', 'ECON'),
                                              ('管理学院', 'MGT'),
                                              ('文学院', 'ART'),
                                              ('法学院', 'LAW'),
                                              ('医学院', 'MED');

-- major 表数据
INSERT INTO `major` (`name`, `code`, `department_id`) VALUES
                                                          ('软件工程', 'SE', 1),
                                                          ('人工智能', 'AI', 1),
                                                          ('应用数学', 'AM', 2),
                                                          ('统计学', 'STAT', 2),
                                                          ('凝聚态物理', 'CMP', 3),
                                                          ('理论物理', 'THEO', 3),
                                                          ('有机化学', 'OC', 4),
                                                          ('分析化学', 'AC', 4),
                                                          ('分子生物学', 'MB', 5),
                                                          ('细胞生物学', 'CB', 5);

-- user 表数据（包含ADMIN/学生/教师）
INSERT INTO `user` (`username`, `password`, `role`) VALUES
                                                        ('admin', 'admin_hash', 1),
                                                        ('S001', 'stu1_hash', 2),
                                                        ('S002', 'stu2_hash', 2),
                                                        ('S003', 'stu3_hash', 2),
                                                        ('S004', 'stu4_hash', 2),
                                                        ('S005', 'stu5_hash', 2),
                                                        ('S006', 'stu6_hash', 2),
                                                        ('S007', 'stu7_hash', 2),
                                                        ('S008', 'stu8_hash', 2),
                                                        ('S009', 'stu9_hash', 2),
                                                        ('S010', 'stu10_hash', 2),
                                                        ('T001', 'tea1_hash', 3),
                                                        ('T002', 'tea2_hash', 3),
                                                        ('T003', 'tea3_hash', 3),
                                                        ('T004', 'tea4_hash', 3),
                                                        ('T005', 'tea5_hash', 3),
                                                        ('T006', 'tea6_hash', 3),
                                                        ('T007', 'tea7_hash', 3),
                                                        ('T008', 'tea8_hash', 3),
                                                        ('T009', 'tea9_hash', 3),
                                                        ('T010', 'tea10_hash', 3);

-- student 表数据（关联user_id 2-11）
INSERT INTO `student` (`student_id`, `student_no`, `name`, `department_id`, `major_id`) VALUES
                                                                                            (2, 'S001', '张三', 1, 1),
                                                                                            (3, 'S002', '李四', 1, 2),
                                                                                            (4, 'S003', '王五', 2, 3),
                                                                                            (5, 'S004', '赵六', 2, 4),
                                                                                            (6, 'S005', '陈七', 3, 5),
                                                                                            (7, 'S006', '周八', 3, 6),
                                                                                            (8, 'S007', '吴九', 4, 7),
                                                                                            (9, 'S008', '郑十', 4, 8),
                                                                                            (10, 'S009', '孙十一', 5, 9),
                                                                                            (11, 'S010', '杨十二', 5, 10);

-- teacher 表数据（关联user_id 12-21）
INSERT INTO `teacher` (`teacher_id`, `teacher_no`, `name`, `department_id`, `major_id`) VALUES
                                                                                            (12, 'T001', '刘教授', 1, 1),
                                                                                            (13, 'T002', '陈副教授', 1, 2),
                                                                                            (14, 'T003', '王讲师', 2, 3),
                                                                                            (15, 'T004', '李助教', 2, 4),
                                                                                            (16, 'T005', '张教授', 3, 5),
                                                                                            (17, 'T006', '赵研究员', 3, 6),
                                                                                            (18, 'T007', '周博士', 4, 7),
                                                                                            (19, 'T008', '吴工程师', 4, 8),
                                                                                            (20, 'T009', '郑导师', 5, 9),
                                                                                            (21, 'T010', '孙院士', 5, 10);

-- thesis 表数据
INSERT INTO `thesis` (`title`, `keywords`, `abstract`, `file_path`, `completion_time`, `status`, `student_id`, `teacher_id`, `department_id`, `major_id`) VALUES
                                                                                                                                                              ('基于深度学习的图像识别研究', '深度学习,图像识别,CNN', '本论文提出...', '/thesis/001.pdf', '2024-06-01', 'EXCELLENT', 2, 12, 1, 1),
                                                                                                                                                              ('量子计算在密码学中的应用', '量子计算,密码学,Shor算法', '本文探讨...', '/thesis/002.pdf', '2024-06-02', 'PASS', 3, 13, 1, 2),
                                                                                                                                                              ('随机过程在金融建模中的实践', '随机过程,金融建模,Black-Scholes', '通过案例分析...', '/thesis/003.pdf', '2024-06-03', 'EXCELLENT', 4, 14, 2, 3),
                                                                                                                                                              ('新型太阳能电池材料研究', '太阳能电池,钙钛矿材料,光电转换', '实验表明...', '/thesis/004.pdf', '2024-06-04', 'FAIL', 5, 15, 2, 4),
                                                                                                                                                              ('超导材料的低温特性分析', '超导材料,临界温度,BCS理论', '通过实验测量...', '/thesis/005.pdf', '2024-06-05', 'PASS', 6, 16, 3, 5),
                                                                                                                                                              ('有机合成新方法的开发', '有机合成,催化剂设计,绿色化学', '提出一种...', '/thesis/006.pdf', '2024-06-06', 'EXCELLENT', 7, 17, 3, 6),
                                                                                                                                                              ('蛋白质结构预测算法优化', '蛋白质折叠,AlphaFold,深度学习', '改进现有...', '/thesis/007.pdf', '2024-06-07', 'PASS', 8, 18, 4, 7),
                                                                                                                                                              ('宏观经济政策对就业的影响', '宏观经济,就业率,VAR模型', '基于面板数据...', '/thesis/008.pdf', '2024-06-08', 'EXCELLENT', 9, 19, 4, 8),
                                                                                                                                                              ('区块链技术在医疗数据共享中的应用', '区块链,医疗数据,隐私保护', '设计新型...', '/thesis/009.pdf', '2024-06-09', 'PASS', 10, 20, 5, 9),
                                                                                                                                                              ('肿瘤免疫治疗新靶点发现', '免疫治疗,PD-1,单克隆抗体', '通过高通量...', '/thesis/010.pdf', '2024-06-10', 'EXCELLENT', 11, 21, 5, 10);