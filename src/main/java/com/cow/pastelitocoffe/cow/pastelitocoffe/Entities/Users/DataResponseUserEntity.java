package com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users;

import java.util.List;

public record DataResponseUserEntity(String name, String email, List<Role> roles) {
}
