!pip install wikipedia

!pip install wordcloud

import wikipedia

wiki = wikipedia.page('Artificial intelligence')
text = wiki.content

from wordcloud import WordCloud
wordcloud =WordCloud(width = 2000,height = 1500).generate(text)

import matplotlib.pyplot as plt
plt.figure(figsize=(10,5))
plt.imshow(wordcloud)
plt.show()

from wordcloud import WordCloud,STOPWORDS
s_words = STOPWORDS.union({'one','using','first','two','make','use'})

from wordcloud import WordCloud
wordcloud =WordCloud(width = 2000,height = 1500,stopwords=s_words).generate(text)

plt.imshow(wordcloud)
plt.show()
