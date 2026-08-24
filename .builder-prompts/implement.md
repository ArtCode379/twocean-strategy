You are running the implementation phase for one Openclaw Android app.

Use these orchestrator instructions as binding context: /home/codex-agent/codex-app-agent/AGENTS.md
Use this screen spec: /home/codex-agent/codex-app-agent/screens-service.md
Project directory: /tmp/twocean-strategy

Task metadata:
- Asana task gid: 1217580748339441
- Asana task name: GB GW4 C1513
- Asana URL: https://app.asana.com/1/1208304498069546/project/1213586227413017/task/1217580748339441
- App name: TWOcean Strategy
- Company: TW OCEAN LTD
- Domain: https://twocean.surf
- Package: twocean.management.twoceanstrategy
- Prefix: HRPIK
- Type: service
- Description: Специфика компании — консультирование по вопросам управления, разработка стратегий развития бизнеса и оптимизация организационных структур.  
Приложение по предложению услуг компании содержит:  
Каталог услуг и решений: список всех направлений управленческого консалтинга (с возможностью сортировки по категориям: например, «Стратегическое планирование», «Управление персоналом», «Повышение операционной эффективности»).  
Портфолио (Галерея): демонстрация успешно реализованных кейсов по трансформации бизнеса и достигнутых ключевых показателей клиентов.  
База знаний: страница с экспертными статьями по теме лидерства, управления изменениями или анализа рыночной конъюнктуры (минимум 3 статьи).  
Логика взаимодействия:  
Бронирование консультации: страница записи на стратегическую сессию или аудит бизнес-процессов с формой (открывается со страницы деталей услуги по кнопке «Забронировать консультацию»).  
Подтверждение: после подтверждения бронирования пользователь видит баннер с информацией о номере и деталях сессии, а также уведомление о том, что консультант будет ожидать его в онлайн-конференции или по адресу офиса в назначенное время.  
Настройки приложения содержат:  
Название компании.  
Версию приложения.  
Раздел Customer Support со ссылкой на сайт компании.

Do Phase 2 and Phase 3 only:
1. Extract or derive the style guide.
2. Do not create project-local agent instruction files inside /tmp/twocean-strategy.
3. Implement all required screens/content/data/assets/icon according to the orchestrator AGENTS.md and the screen spec.
4. Icon generation is best-effort: if Leonardo/imagegen cannot provide a filesystem-backed icon quickly, continue implementing the app with existing assets.
5. Do not push to GitHub, do not update Asana, and do not send Slack.
6. You may run local checks while implementing, but the runner will run quality/build afterward.
7. Keep every Kotlin file conventionally formatted: one statement per line, annotations above declarations, expanded indented Compose blocks, no semicolon-compressed code, and no source line longer than 200 characters.
