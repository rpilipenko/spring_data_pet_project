package com.game.service;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.PlayerValidationException;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Date;

@Service
public class PlayerValidationService {

    public void validateParamsOfCreation(Player player) {
        if (player == null) {
            throw new PlayerValidationException();
        }
        validateName(player.getName());
        validateTitle(player.getTitle());
        validateRace(player.getRace());
        validateProfession(player.getProfession());
        validateBirthday(player.getBirthday());
        validateBanned(player.getBanned());
        validateExperience(player.getExperience());
    }


    public void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new PlayerValidationException();
        }
    }

    public void validateName(String name) {
        if (name == null || name.isEmpty() || name.length() > 12) {
            throw new PlayerValidationException();
        }
    }

    public void validateTitle(String title) {
        if (title == null || title.isEmpty() || title.length() > 30) {
            throw new PlayerValidationException();
        }
    }

    public void validateRace(Race race) {
        if (race == null) {
            throw new PlayerValidationException();
        }
    }

    public void validateProfession(Profession profession) {
        if (profession == null) {
            throw new PlayerValidationException();
        }
    }

    public void validateExperience(Integer experience) {
        if (experience == null || experience < 0 || experience > 10_000_000) {
            throw new PlayerValidationException();
        }
    }

    public void validateBirthday(Date date) {
        if (date == null || date.toInstant().atOffset(ZoneOffset.UTC).getYear() < 2000
                || date.toInstant().atOffset(ZoneOffset.UTC).getYear() > 3000) {
            throw new PlayerValidationException();
        }
    }

    public void validateBanned(Boolean banned) {
        if (banned == null) {
            throw new PlayerValidationException();
        }
    }

    public void validateLevel(Integer level) {
        if (level == null || level <= 0) {
            throw new PlayerValidationException();
        }
    }

}
/*

    Long id
    ID игрока
    String name

    Имя персонажа(до 12знаков включительно)

    String title

    Титул персонажа(до 30знаков включительно)

    Race race
    Расса персонажа
    Profession profession
    Профессия персонажа
    Integer experience
    Опыт персонажа.Диапазон значений0..10,000,000
        Integer level
        Уровень персонажа
        Integer untilNextLevel
        Остаток опыта до следующего уровня
        Date birthday
        Дата регистрации
        Диапазон значений года2000..3000включительно
        Boolean banned
        Забанен/не забанен
        Также
        1.Если в запросе на создание игрока нет параметра “banned”,то считаем,что пришло значение “false”.
        2.Параметры даты между фронтом и сервером передаются в миллисекундах(тип Long)начиная с01.01.1970.
        3.При обновлении или создании игрока игнорируем параметры “id”, “level” и “untilNextLevel” из тела запроса.
        4.Если параметр order не указан – нужно использовать значение PlayerOrder.ID.
        5.Если параметр pageNumber не указан – нужно использовать значение0.
        6.Если параметр pageSize не указан – нужно использовать значение3.
        7.Не валидным считается id,если он:
        -не числовой
        -не целое число
        -не положительный
        8.При передаче границ диапазонов(параметры с именами,которые начинаются на «min» или «max»)границы нужно использовать включительно.


        Мы не можем создать игрока,если:
        -указаны не все параметры из Data Params(кроме banned);
        -длина значения параметра “name” или “title” превышает размер соответствующего поля в БД(12и 30символов);
        -значение параметра “name” пустая строка;
        -опыт находится вне заданных пределов;
        - “birthday”:[Long]< 0;
        -дата регистрации находятся вне заданных пределов.
        В случае всего вышеперечисленного необходимо ответить ошибкой с кодом400.

        Если игрок не найден в БД,необходимо ответить ошибкой с кодом404.
        Если значение id не валидное,необходимо ответить ошибкой с кодом400.

        Обновлять нужно только те поля,которые не null.
        Если игрок не найден в БД,необходимо ответить ошибкой с кодом404.
        Если значение id не валидное,необходимо ответить ошибкой с кодом400.

*/
