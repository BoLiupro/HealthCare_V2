import requests
import re

url="http://www.weather.com.cn/weather1d/101230101.shtml"
headers={
    "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.3 Safari/605.1.15"
}
resp=requests.get(url=url, headers=headers )
resp.encoding = 'utf-8'
resp.close()
str=resp.text

obj1=re.compile(r'<p>(?P<abc>.*?)</p>')
indexs = obj1.finditer(str)
dic={}
lst=["感冒指数","运动指数","过敏指数","穿衣指数","洗车指数","辐射指数"]
count=0;
for it in indexs:
    dic[lst[count]]=it.group("abc")
    count=count+1
    if(count==6): break
dic.pop("洗车指数")

