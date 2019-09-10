# Задача
Написать мобильное приложение, которое отображет список контактов и выполняет поиск по ним.
# Описание
На старте приложение получает массив контактов из нескольких источников: [источник1](json/generated-01.json), [источник2](json/generated-02.json), [источник3](json/generated-03.json) и сохраняет их локально.
В процессе загрузки контактов на экране отображается круговой ProgressBar.
Если приложение запускается не в первый раз, и с момента прошлой загрузки данных прошло более 1 минуты, то данные необходимо загрузить заново, иначе нужно показать данные, сохраненные локально.
Список контактов можно обновить свайпом вниз. Если при загрузке или обновлении данных происходит ошибка, то нужно показать ее с помощью Snackbar.
Поиск среди контактов происходит по имени или номеру телефона. Результаты поиска появляются по мере ввода символов в строку поиска и могут отображаться в основном списке. При клике на контакт открывается экран с более подробной информацией. Клик по номеру телефона открывает стандартный набор номера.
