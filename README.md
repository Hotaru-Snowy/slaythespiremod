# slaythespiremod
制作一个战双的丽芙mod<br>
仅供学习参考使用，代码东拼西凑的，素材南捡北扒，如有雷同那就是我抄的。<br>
如果侵犯了您的权益，请联系本人(Hotaru-Snowy)，我会尽快整改。<br>
<h2>关于使用者</h2>
使用须知：拉取到本地用maven构建即可。<br>
目前进度：
能运行√<br>
角色：1<br>
卡牌数量：6<br>
遗物数量：1<br>
BUFF数量：7<br>
<h2>关于开发备忘录</h2>
<h3>卡牌添加</h3>
普通卡牌添加需要继承CustomCard，并且至少需要实现super()，use()，upgrade()，makeCopy()。<br>
需要在Main中注册这张卡牌<br>
需要在Cards.json中添加名字和描述<br>
需要添加图片卡面(500*380,250*190)。<br>
<br>另外的，信号球卡需要实现normalUse()，tripleSignUse()来代替use()<br>
<h3>遗物添加</h3>
遗物添加需要继承CustomRelic，并且至少需要实现super()，updateDescription()。具体功能按需求实现。<br>
需要在Main中注册这个遗物<br>
需要在Relics.json中添加名字和描述<br>
需要添加遗物图片(图片大小64*64，里面实际内容大概是一半)。<br>
<h3>BUFF添加</h3>
添加BUFF继承的是AbstractPower（为啥没有CustomPower），并且至少需要实现构造方法，updateDescription()。具体功能按需求实现。<br>
不需要注册这个BUFF，需要在Powers.json中添加名字和描述<br>
需要添加buff图标，大图标64*64。小图标32*32<br>
<h2>未来计划</h2>
1.把卡牌填充满<br>
2.把遗物填充满<br>
3.修改人物立绘<br>
4.修改其他材质<br>
5.修改人物动作<br>
6.修改平衡性<br>
7.把信号球系统独立出来作为前置mod<br>
