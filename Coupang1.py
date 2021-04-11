import requests 
import re
from bs4 import BeautifulSoup 
for i in range(10):
    print("현재 페이지", i+1)
    url = "https://www.coupang.com/np/search?q=%EC%BD%9C%EB%9D%BC%EA%B2%90&channel=user&component=&eventCategory=SRP&trcid=&traid=&sorter=scoreDesc&minPrice=&maxPrice=&priceRange=&filterType=&listSize=36&filter=&isPriceRange=false&brand=&offerCondition=&rating=0&page={}&rocketAll=false&searchIndexingToken=1=4&backgroundColor=".format(i+1)
    headers = {"User-Agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.67 Safari/537.36"}
    res = requests.get(url,headers=headers)
    res.raise_for_status()
    soup = BeautifulSoup(res.text,"lxml")  
    items =  soup.find_all("li",attrs = {"class":re.compile("^search-product")})
    # print(items[0].find("div",attrs = {"class":"name"}).get_text())
    for item in items: 
        #광고는 제외한다 
        ad_badge = item.find("span", attrs = {"class": "ad-badge-text"})
        if ad_badge: 
            # print("<광고 상품 제외 합니다>")
            continue

        name = item.find("div",attrs = {"class":"name"}).get_text()   #제품명
        #애플 제품 제외 
        if "Apple" in name: 
            # print("    Apple 상품 제외 합니다")
            continue

        price = item.find("strong", attrs = {"class":"price-value"}).get_text()   #가격 

        #리뷰 200개 이상, 평점 4.5 이상 조회 

        rate = item.find("em", attrs = {"class":"rating"}) #평점
        if rate: 
            rate = rate.get_text() 
            
        else: 
            # print("    평점 없는 제품 제외 합니다")
            continue 
        rate_count = item.find("span", attrs={"class":"rating-total-count"})
        if rate_count: 
            rate_count = rate_count.get_text()
            rate_count = rate_count[1:-1 ]
            # print("리뷰 수", rate_count)
        else: 
            # print("    평점 수 없는 상품은 제외합니다 ") 
            continue  
        link = item.find("a", attrs={"class":"search-product-link"})["href" ]
        if float(rate)>=4.8: 
            if float(rate_count)>=200:
                # print(name+"/"+price+"/"+rate+"/"+rate_count)
                print(f"제품명 : {name}")
                print(f"가격 : {price}")
                print(f"평점 : {rate}점 ({rate_count}개)")
                print("바로가기 : {}".format("https://www.coupang.com" + link))
                print("-"*100) #줄긋기 
