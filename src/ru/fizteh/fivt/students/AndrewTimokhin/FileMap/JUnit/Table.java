package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.util.List;

public interface Table {

    /**
     * ���������� �������� ���� ������.
     */
    String getName();

    /**
     * �������� �������� �� ���������� �����.
     *
     * @param key
     *            ����.
     * @return ��������. ���� �� �������, ���������� null.
     * @throws KeyNullAndNotFound
     *
     * @throws IllegalArgumentException
     *             ���� �������� ��������� key �������� null.
     */
    String get(String key) throws IllegalArgumentException, KeyNullAndNotFound;

    /**
     * ������������� �������� �� ���������� �����.
     *
     * @param key
     *            ����.
     * @param value
     *            ��������.
     * @return ��������, ������� ���� �������� �� ����� ����� �����. ���� �����
     *         �������� �� ���� ��������, ���������� null.
     *
     * @throws IllegalArgumentException
     *             ���� �������� ���������� key ��� value �������� null.
     */
    String put(String key, String value);

    /**
     * ������� �������� �� ���������� �����.
     *
     * @param key
     *            ����.
     * @return ��������. ���� �� �������, ���������� null.
     *
     * @throws IllegalArgumentException
     *             ���� �������� ��������� key �������� null.
     */
    String remove(String key);

    /**
     * ���������� ���������� ������ � �������.
     *
     * @return ���������� ������ � �������.
     */
    int size();

    /**
     * ��������� �������� ���������.
     *
     * @return ���������� ���������� ������.
     */
    int commit();

    /**
     * ��������� ����� ��������� � ������� ��������� ��������.
     *
     * @return ���������� ��������� ������.
     */
    int rollback();

    /**
     * ������� ������ ������ �������
     *
     * @return ������ ������.
     */
    List<String> list();
}
