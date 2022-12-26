import requests
import re
import pymysql

# db = pymysql.connect(host="localhost", user="root",password= "66666666", database="healthcare", charset='utf8' )
db = pymysql.connect(host="123.57.213.188", user="root",password= "123456", database="healthcare", charset='utf8' )
cursor = db.cursor()
headers={
    "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.3 Safari/605.1.15"
}
key=("注射液","头孢","酸","阿莫西林","氨基酸","血塞通")
for u in range(0,5):
    for i in range(1,50):
        url="http://www.china-yao.com/?act=search&typeid=1&keyword="+key[u]+"&page="+str(i)
        resp=requests.get(url=url, headers=headers )
        resp.encoding = 'utf-8'
        resp.close()
        str1=resp.text
        obj1=re.compile(r'<tr>\s+<td>(?P<name>.*?)</td>\s+<td>(?P<type>.*?)</td>\s+<td>(?P<quantity>.*?)</td>\s+'
                        r'<td>(?P<price1>.*?)\s+<td>(?P<price2>.*?)\s+</td>\s+<td>(?P<producer>.*?)</td>')
        details = obj1.finditer(str1)
        dic={}
        for it in details:
            sql = """insert into drugs (name,type,quantity,price,producer) values (%s,%s,%s,%s,%s)"""
            values=(it.group("name"),it.group("type"),it.group("quantity"),it.group("price2"),it.group("producer"))
            cursor.execute(sql,values)
            db.commit()
    # 关闭数据库连接
db.close()