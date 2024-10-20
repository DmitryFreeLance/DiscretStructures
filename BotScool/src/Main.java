import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.*;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static final String BOT_TOKEN = "7647662943:AAEAySgnNaCSd-fxuPyRCK26D3zoKYR8H2c";
    public static LogService logService = new LogService();
    public static Map<Long, TGUser> users = new HashMap<>();
    public static TelegramBot telegramBot = new TelegramBot(BOT_TOKEN);

    public static void main(String[] args) {
        telegramBot.setUpdatesListener(list -> {
            for (Update update : list) {
                if (update.callbackQuery() != null) {
                    handleCallbackQuery(update.callbackQuery());
                } else {
                    newMessageFromUser(update);
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public static void handleCallbackQuery(CallbackQuery callbackQuery) {
        Long userId = callbackQuery.from().id();
        String callbackData = callbackQuery.data();

        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(callbackQuery.id());
        telegramBot.execute(answerCallbackQuery);

        switch (callbackData) {
            case "get_gift":
                sendGiftMessage(userId);
                break;
            case "start_test":
                startTest(userId);
                break;
            case "lessons":
                sendLessonsInfo(userId, getBackToMainMenuKeyboard());
                break;
            case "check_child":
                checkChildPreparation(userId);
                break;
            case "feedback":
                sendFeedbackRequest(userId);
                break;
            case "more_feedback":
                sendFeedbackRequest(userId);
                break;
            case "send_pdf":
                sendPDF(userId, getBackToMainMenuKeyboard());
                break;
            case "back_to_main":
                sendMainMenu(userId);
                break;
            default:
                break;
        }
    }

    // New method to get keyboard with /start button
    private static InlineKeyboardMarkup getMainKeyboard() {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        InlineKeyboardButton startButton = new InlineKeyboardButton("/start").callbackData("start_test"); // Adjust accordingly if needed

        inlineKeyboard.addRow(startButton);
        return inlineKeyboard;
    }

    private static InlineKeyboardMarkup getBackToMainMenuKeyboard() {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        InlineKeyboardButton backButton = new InlineKeyboardButton("Вернуться назад").callbackData("back_to_main");
        inlineKeyboard.addRow(backButton);
        return inlineKeyboard;
    }

    private static void sendGiftMessage(Long userId) {
        SendMessage sendMessage = new SendMessage(userId, "\uD83C\uDF81 Вот ваш первый подарок - бесплатная диагностика знаний в режиме онлайн. Свяжитесь с менеджером для записи на удобное для вас время.");
        sendMessage.replyMarkup(getBackToMainMenuKeyboard());
        telegramBot.execute(sendMessage);
    }

    public static void sendPDF(Long userId, InlineKeyboardMarkup keyboard) {
        java.io.File pdfFile = new java.io.File("BotScool/src/Чек-лист .pdf");
        SendDocument sendDocument = new SendDocument(userId, pdfFile);
        sendDocument.replyMarkup(keyboard);
        telegramBot.execute(sendDocument);
    }

    private static void sendLessonsInfo(Long userId, InlineKeyboardMarkup keyboard) {
        java.io.File videoFile = new java.io.File("BotScool/src/Lessons.mp4");
        SendVideo sendVideo = new SendVideo(userId, videoFile);
        sendVideo.replyMarkup(keyboard);
        telegramBot.execute(sendVideo);
    }

    private static void checkChildPreparation(Long userId) {
        SendMessage sendMessage = new SendMessage(userId, "Проверка подготовки ребенка");
        sendMessage.replyMarkup(getBackToMainMenuKeyboard());
        telegramBot.execute(sendMessage);
    }

    private static int feedbackIndex = 0; // Track current feedback video index

    private static void sendFeedbackRequest(Long userId) {
        String[] feedbackVideos = {
                "BotScool/src/1.mp4",
                "BotScool/src/2.mp4",
                "BotScool/src/3.mp4",
                "BotScool/src/4.mp4",
                "BotScool/src/5.mp4"
        };

        if (feedbackIndex >= feedbackVideos.length) {
            feedbackIndex = 0;
        }

        java.io.File videoFile = new java.io.File(feedbackVideos[feedbackIndex]);
        SendVideo sendVideo = new SendVideo(userId, videoFile);

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        InlineKeyboardButton backButton = new InlineKeyboardButton("Вернуться назад").callbackData("back_to_main");

        if (feedbackIndex < feedbackVideos.length - 1) {
            InlineKeyboardButton moreFeedbackButton = new InlineKeyboardButton("Больше отзывов").callbackData("more_feedback");
            inlineKeyboard.addRow(moreFeedbackButton, backButton);
        } else {
            inlineKeyboard.addRow(backButton);
        }

        sendVideo.replyMarkup(inlineKeyboard);
        telegramBot.execute(sendVideo);
        feedbackIndex++;
    }

    private static void startTest(Long userId) {
        resetUserState(userId);
        TGUser user = users.get(userId);

        if (user == null) {
            user = new TGUser(userId);
            users.put(userId, user);
        }

        // Mark the user as in a test session
        user.setInTestSession(true);

        // Start sending questions
        if (!questionMessage(userId, user, null)) {
            resultMessage(userId, user);
            sendBackButton(userId);
        }
    }

    public static void newMessageFromUser(Update update) {
        // Check if the update contains a message before logging
        if (update.message() != null) {
            logService.log(update); // Log only when there's a message

            Long userId = update.message().from().id();
            TGUser tgUser = users.get(userId);

            // Check if the user is taking a test
            if (tgUser != null && tgUser.isInTestSession()) {
                // Process the answer
                if (!questionMessage(userId, tgUser, update)) {
                    // If there are no more questions, end the test
                    resultMessage(userId, tgUser);
                    tgUser.setInTestSession(false); // Mark the end of the test
                }
            } else {
                // If the user is not taking a test, show the main menu
                if (tgUser == null) {
                    users.put(userId, new TGUser(userId));  // Add new user
                }
                sendMainMenu(userId);  // Show main menu
            }
        } else {
            // Optionally log or handle updates that do not contain a message (e.g., callback queries)
            System.out.println("Received update without a message.");
        }
    }


    public static Boolean startMessage(Long userId) {
        if (!users.containsKey(userId)) {
            users.put(userId, new TGUser(userId));

            // Send the first-time menu (video + regular buttons)
            sendMainMenu(userId);
            return true;
        }

        return false;
    }

    public static void sendMainMenu(Long userId) {
        // Get user from the map
        TGUser user = users.get(userId);

        // Check if user exists, create if not
        if (user == null) {
            user = new TGUser(userId);
            users.put(userId, user);
        }

        // Send the main message
        SendMessage sendMessage = new SendMessage(userId, Text.HelloMessage);
        sendMessage.parseMode(ParseMode.Markdown);

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();

        // Adding buttons
        InlineKeyboardButton giftButton = new InlineKeyboardButton("Получить подарок").callbackData("get_gift");
        InlineKeyboardButton testButton = new InlineKeyboardButton("Пройти тестирование").callbackData("start_test");
        InlineKeyboardButton lessonsButton = new InlineKeyboardButton("Как проходят уроки").callbackData("lessons");
        InlineKeyboardButton videoButton = new InlineKeyboardButton("Проверить готовность ребенка").callbackData("send_pdf");
        InlineKeyboardButton feedbackVideoButton = new InlineKeyboardButton("Видео с отзывами").callbackData("feedback");
        InlineKeyboardButton telegramContactButton = new InlineKeyboardButton("Связь с менеджером в Telegram")
                .url("https://t.me/kidscool");

        // Adding buttons to keyboard
        inlineKeyboard.addRow(giftButton);
        inlineKeyboard.addRow(testButton);
        inlineKeyboard.addRow(lessonsButton);
        inlineKeyboard.addRow(videoButton);
        inlineKeyboard.addRow(feedbackVideoButton);
        inlineKeyboard.addRow(telegramContactButton);

        // Only send video if the user is new
        if (user.isNewUser()) {
            telegramBot.execute(sendMessage);
            java.io.File videoFile = new java.io.File("BotScool/src/hello2.mp4");
            SendVideo sendVideo = new SendVideo(userId, videoFile);
            sendVideo.replyMarkup(inlineKeyboard);
            telegramBot.execute(sendVideo);

            user.setNewUser(false);  // Set the user as no longer new
        } else {
            // If the user is not new, just send the main menu message
            sendMessage.replyMarkup(inlineKeyboard);
            telegramBot.execute(sendMessage);
        }
    }

    public static Boolean questionMessage(Long userId, TGUser user, Update update) {
        for (Question question : user.getQuestions()) {
            if (question.getSendQuestionToUser()) {
                if (!question.getHasAnswer()) {
                    question.setHasAnswer(true);
                    for (Answer answer : question.getAnswers()) {
                        if (answer.getText().equals(update.message().text())) {
                            if (answer.getUserLevel().equals(EnglishLevel.Points)) {
                                user.setEnglishLevelPoints(user.getEnglishLevelPoints() + 1);

                                SendMessage sendMessage = new SendMessage(userId, Text.correct);
                                telegramBot.execute(sendMessage);
                            }

                            if (answer.getUserLevel().equals(EnglishLevel.WrongPoints)) {
                                user.setEnglishLevelWrongPoints(user.getEnglishLevelWrongPoints() + 1);

                                SendMessage sendMessage = new SendMessage(userId, Text.incorrect);
                                telegramBot.execute(sendMessage);
                            }
                        }
                    }
                }
            }

            if (!question.getSendQuestionToUser()) {
                question.setSendQuestionToUser(true);
                SendMessage sendMessage = new SendMessage(userId, question.getText());
                sendMessage.parseMode(ParseMode.Markdown);

                // Only set the keyboard when sending a question
                String[][] keyButtons = new String[question.getAnswers().size()][1];
                for (int i = 0; i < question.getAnswers().size(); i++) {
                    keyButtons[i][0] = question.getAnswers().get(i).getText();
                }

                Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(keyButtons)
                        .oneTimeKeyboard(true) // Optional: Makes the keyboard disappear after selection
                        .resizeKeyboard(true)
                        .selective(true);

                sendMessage.replyMarkup(replyKeyboardMarkup);
                telegramBot.execute(sendMessage);
                return true;
            }
        }

        return false;
    }

    public static void resultMessage(Long userId, TGUser user) {
        String resultMessageText = "";
        int pointsResult = user.getEnglishLevelPoints();  // Считаем результат

        // Определяем уровень пользователя на основе набранных баллов
        if (pointsResult < 4) {
            resultMessageText = Text.RESULT_MESSAGE_BEGINNER;
        } else if (pointsResult < 6) {
            resultMessageText = Text.RESULT_MESSAGE_ELEMENTARY;
        } else if (pointsResult < 11) {
            resultMessageText = Text.RESULT_MESSAGE_INTERMEDIATE;
        } else if (pointsResult < 16) {
            resultMessageText = Text.RESULT_MESSAGE_UPPER_INTERMEDIATE;
        } else if (pointsResult < 20) {
            resultMessageText = Text.RESULT_MESSAGE_ADVANCED;
        }

        // Формируем итоговое сообщение
        String finalMessage = Text.RESULT_MESSAGE1 + user.getEnglishLevelPoints() + " / 19" + "\n" + resultMessageText + "\n" + Text.RESULT_MESSAGE3;

        // Создаем кнопки
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        InlineKeyboardButton retryButton = new InlineKeyboardButton("Пройти тест еще раз").callbackData("start_test");
        InlineKeyboardButton backButton = new InlineKeyboardButton("Вернуться в начало").callbackData("back_to_main");

        // Добавляем кнопки на клавиатуру
        inlineKeyboard.addRow(retryButton);
        inlineKeyboard.addRow(backButton);

        // Отправляем сообщение с результатом и кнопками
        SendMessage sendMessage = new SendMessage(userId, finalMessage);
        sendMessage.replyMarkup(inlineKeyboard);
        telegramBot.execute(sendMessage);

        user.setNewUser(false);
    }

    public static void resetUserState(Long userId) {
        TGUser user = new TGUser(userId);
        user.setInTestSession(false); // Reset the test session flag
        users.put(userId, user);
    }

    // Adding "Back" button for each menu section
    public static void sendBackButton(Long userId) {
        InlineKeyboardMarkup inlineKeyboard = getMainKeyboard(); // Adding the main keyboard that includes the /start button
        InlineKeyboardButton backButton = new InlineKeyboardButton("Вернуться назад").callbackData("back_to_main");
        inlineKeyboard.addRow(backButton);
        SendMessage sendMessage = new SendMessage(userId, "Выберите действие:");
        sendMessage.replyMarkup(inlineKeyboard);
        telegramBot.execute(sendMessage);
    }
}