
import csv

import numpy as np
import pandas as pd

filename ="/Users/liubo/Desktop/drugs.csv"
data = []
with open(filename) as csvfile:
    csv_reader = csv.reader(csvfile)  # 使用csv.reader读取csvfile中的文件
    # header = next(csv_reader)        # 读取第一行每一列的标题
    for row in csv_reader:  # 将csv 文件中的数据保存到data中
        data.append(row[1])  # 选择某一列加入到data数组中
    print(data,"\n")


def entropyWeight(data):
    pass


def topsis(data, weight=None):
    # 权重
    weight = entropyWeight(data) if weight is None else np.array(weight)

    # 最优最劣方案
    Z = pd.DataFrame([(data * weight.T).min(), (data * weight.T).max()], index=['负理想解', '正理想解'])
    # Z = pd.DataFrame([data.min(), data.max()], index=['负理想解', '正理想解'])

    # 距离
    Result = data.copy()
    # Result['正理想解'] = np.sqrt(((data - Z.loc['正理想解']) ** 2 * weight).sum(axis=1))
    # Result['负理想解'] = np.sqrt(((data - Z.loc['负理想解']) ** 2 * weight).sum(axis=1))
    Result['正理想解'] = np.sqrt(((weight * data - Z.loc['正理想解']) ** 2).sum(axis=1))
    Result['负理想解'] = np.sqrt(((weight * data - Z.loc['负理想解']) ** 2).sum(axis=1))

    # 综合得分指数
    Result['综合得分指数'] = Result['负理想解'] / (Result['负理想解'] + Result['正理想解'])
    Result['排序'] = Result.rank(ascending=False)['综合得分指数']

    return Result, Z, weight
