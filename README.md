[![CSDN](https://img.shields.io/badge/CSDN-@xiaolongonly-blue.svg?style=flat)](http://blog.csdn.net/guoxiaolongonly)
[![PersonBlog](https://img.shields.io/badge/PersonBlog-@xiaolongonly-blue.svg?style=flat)](http://xiaolongonly.cn/)

## 2018年11月19日 10:59:14更新

	最近有人反馈项目不能用了，我做了一个版本的更新。
	
	这个项目只是一个给开发者演示的demo项目。希望大家不要直接拿这个项目做商业用途。
	由于旧版本密钥可能被拿去商业化，目前调用频繁，现在更新了密钥，APP能正常使用，由于调用次数有限，如果大家对API有兴趣的话可以到万维易源平台https://www.showapi.com/api/view/44 申请一个属于自己的API，不要使用这个APP中的key，好让大家能共享项目。



# 实现如下
![image](https://github.com/xiaolongonly/Ticket-Analysis/blob/master/operate.gif)

初版历时半个多月

## 基础功能

1. 开奖结果查询
 - 近期开奖查询
 - 历史开奖查询（最多五十期）

2. 关注彩种
3. 一些简单的趋势分析
4.号码预测（号码预测做的比较简单，直接算出每个号码的多期平均值，和期望平均值做对比。取均值。理论上应该是范围内的都是概率发生的，这一块其实可以加入奇偶频率，号码频率，和一些其他的条件来做预测，后面会继续做优化）
5. 接口原因，能用到的接口只有四个。 自己编写了规则文档。还有一些其他必要的功能。
6.最后面想说的是这里其实更需要一个后台来爬取数据做动态更新。（打算写一个后台和爬虫来搞）




# 设计意图

设计意图来自身边的朋友，对这一块投注存在的需求。

### 随机组合出所有可能再做筛选，然后投注

起初有个朋友让我帮忙写一个体育彩票36选7三个数字固定，其余数字随机的可能结果，然后动了一下脑子想了一下，
这些数据有
(33 X 32 X 31 X 30)/(4 X 3 X 2 X 1) = 40920种，
如果整组号码随机组合的结果有
(36 X 35 X 34 X 32 X 31 X 30)/(7 X 6 X 5 X 4 X 3 X 2 X 1) = 8347680种 
由此可见其中大奖概率是很低的


特等奖(7)  中奖概率为 概率公式打不出来((C7 7) / (C36 7)) = 1/8347680 (全复式共1注)

一等奖(6+1)  (((C8 7) *(C28 0)-(C7 7))/(C36 7)) = 1/1192526（全复式共7注）

二等奖(6)   1/42590(全复式共中196注)

三等奖(5+1) 1/14197(全复式共中588注)

四等奖(5)   1/1052(全复式共中7938注)

五等奖(4+1) 1/631(全复式共中13230注)

六等奖(4)   1/73(全复式共中114660注)

无奖(3+1)   1/73（全复式共中114660注）

无奖(3)     1/12（全复式共中716625注）

无奖(2+1)   1/19（全复式共中429975注）

无奖(2)     1/4（全复式共中2063880注）

无奖(1+1)   1/12（全复式共中687960注）

无奖(1)     1/3（全复式共中2637180注）

无奖(0+1)   1/22（全复式共中376740注）

下面给出这类彩票的中奖概率计算公式

n个全是正选号码：![image](https://github.com/xiaolongonly/Ticket-Analysis/blob/master/7.png)

n个正选加一个特别号码：![image](https://github.com/xiaolongonly/Ticket-Analysis/blob/master/6%2B1.png) - ![image](https://github.com/xiaolongonly/Ticket-Analysis/blob/master/7.png)

想起了我大学的时候的概率统计，学概率统计的时候老师就说过了，赌博（指合法的彩票这类的），还有保险这个行业，基本上都能从概率学来计算，而且如果仔细算一下这里面的概率，就能发现这些都是非常暴利的行业。好吧，没研究过体彩的盈利手段，可能都是从税上面征的。而保险其实如果有经过大规模的数据分析的话，是可以知道赔保的概率很低，所以基本上买保险的人数乘以保险额会远远大于预估出来的全部需要出险的赔偿额的，剩下的都是利润利润。
如果从心理学上面来讲的话，买保险大家都是图个心安，买彩票则是赌。不禁感叹人类果然智慧无限，对这两类人群都做了细致的划分和业务推销..扯远了扯远了..

回归正题吧，可见如果要买到所有号码可能就血本无归了，基本是赢不了啦。

在聊下这个朋友，这个朋友后面需要我帮忙演算一下多期开奖结果的均值，也就是一万期，十万期...甚至几千万期之后的开奖均值，模拟演算了一下36选7一千万组数据的均值大概在129.495左右。


### 靠近均值附近中奖的概率高

理论上来讲既然均值是这个值，如果能通过数据证明大多数的开奖数据都会比较接近这个均值，就是分布概率会在均值附近，是不是就能证明往靠近均值的地方买彩票中奖率会高一些。
算一下最小值 1+2+3+4+5+6+7=28   最大值 30+31+32+33+34+35+36=241这些都只有一组，而越往中间，选择越多如果是 30的话就可以1+2+3+4+5+6+9/1+2+3+4+5+7+8 ..是有这个趋势的。不过从另外一个维度分析，正因为均值区域概率太大，在这一块的组合也多，也就是说在改区域块的中奖概率也跟这里面的组合数是一致的。最终结果还是每个开奖的概率都相等，成功把自己扳倒了...

### 如果每个号码的开奖概率相同，那么买出现概率最低的码是不是就能中奖

想起小时候，跟朋友玩猜大小游戏的时候如果小的次数开多了，他们就会一直猜大，甚至加注猜大，坐庄的我每次都被搞的慌的要死，虽然知道大小概率还是50%，但也没办法，因为确实是很邪门的一件事情。

在一千万组数据下来的时候会发现，每一个号码的开奖次数都是接近相等的，现在假设我有（1-10） 10个号码，10选1，一千期内出现的概率为  100次 102次 105 次 98次 95次 93次 112次 88次 97次 94次 96次，根据期数多了每个号码开奖概率会趋于相等的原理，如果一直买 88次的是不是就稳赢了，好吧，如果学过概率统计就知道独立事件是互不影响的。不要想太多。更何况36/7的组合想要不让最低号码全中奖一直到开奖概率平均也是能做到的。

# 需求分析

帮朋友做了这两个应用之后，开始研究这方面的需求，感觉现在的市场确实会有这方面的分析需求。
那我要做些什么功能来满足他们呢。

1.求平均，不管是35/7 ,22/5或是其他 做一个千万把求平均的功能（已完成，仅支持，不重复，无颜色随机）

2.随机摇号 做一个随机摇号的功能，当然生成数量有限 (已完成)

3.最重要的功能，开奖结果查询，将每一种彩票的开奖结果罗列出来。(已完成)

4.开奖结果分析，用折线图来展示开奖结果，比如说多期内同一个号码的开奖次数统计（已完成）

5.每一期的平均统计等 （已完成）

6.其他 （号码收藏后期会添加）

# Then Coding...

项目已经开发完成，除特殊情况，项目不在维护。有Android相关的开发合作可联系QQ：719243738

如果你喜欢本项目，可以发个红包支持一下作者。

wx
![image](https://github.com/xiaolongonly/Ticket-Analysis/blob/master/wx.png)

